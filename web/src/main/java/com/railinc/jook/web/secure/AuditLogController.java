package com.railinc.jook.web.secure;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.railinc.common.hibernate.AuditLogRecord;
import com.railinc.common.hibernate.AuditLogService;
import com.railinc.jook.web.util.StandardController;

@Controller
@RequestMapping("secure/auditlog")
public class AuditLogController extends StandardController {
	public static final String VIEW_INTERACTION_OBJECTHISTORY = ".view.snippet.objectHistory";
	public static final String VIEW_INTERACTION_TXHISTORY = ".view.snippet.txHistory";
	
	
	AuditLogService service = null;


	public AuditLogService getService() {
		return service;
	}


	public void setService(AuditLogService service) {
		this.service = service;
	}

//	@InitBinder
//	public void initBinder(WebDataBinder binder){
//		binder.registerCustomEditor(Set.class, "applications", new SetOfStringEditor());
//	}

	
	
	
//	@RequestMapping(method=RequestMethod.GET,value="details")
//	public String showInteractionDetails(StaticInteraction interaction, Model model)  {
//		StaticInteraction productByName = service.getInteraction(interaction.getId());
//		 
//		model.addAttribute("interaction", productByName);
//
//		
//		return VIEW_INTERACTION_DETAILS;
//	}

	@RequestMapping(method=RequestMethod.GET,value="snippet/txhistory")
	public String transaction(@RequestParam("tx") String txRef, Model model) {
		List<String> entityTypesInvolvedInTx = service.entityTypesInvolvedInTx(txRef);

		// map of entity type > map<entity id, list of auditlog records>
		Map<String, Map<String,List<AuditLogRecord>>> full = new HashMap<String, Map<String,List<AuditLogRecord>>>();

		String updatedBy = null;
		Date updatedDate = null;
		
		// this really should just pull ALL records for the tx and then have a useful
		// collection wrapper object that would provide access ot the items in different ways
		for (String entityName : entityTypesInvolvedInTx) {
			List<String> entityIdsInvolvedInTx = service.entityIdsInvolvedInTx(txRef, entityName);
			for (String entityId : entityIdsInvolvedInTx) {
				List<AuditLogRecord> records = service.records(entityName, entityId, txRef);
				updatedBy = records.iterator().next().getUpdatedBy();
				updatedDate = records.iterator().next().getUpdatedDate();
				Map<String, List<AuditLogRecord>> recsById = full.get(entityName);
				if(null == recsById) {
					recsById = new HashMap<String, List<AuditLogRecord>>();
					full.put(entityName, recsById);
				}
				recsById.put(entityId, records);
			}
		}
		
		model.addAttribute("entityNames", entityTypesInvolvedInTx);
		model.addAttribute("tx", txRef);
		model.addAttribute("model", full);
		model.addAttribute("updatedBy", updatedBy);
		model.addAttribute("updatedDate",updatedDate);
		return VIEW_INTERACTION_TXHISTORY;
	}
	

	@RequestMapping(method=RequestMethod.GET,value="snippet/objecthistory")
	public String objecthistory(
			@RequestParam("objType") String entityName,
			@RequestParam("objId") String entityId, 
			Model model) {
		
		List<String> txReferences = service.txReferences(entityName, entityId);
		Map<String,List<AuditLogRecord>> records = new HashMap<String,List<AuditLogRecord>>();
		for (String txRef : txReferences) {
			List<AuditLogRecord> rs = service.records(entityName, entityId, txRef);
			records.put(txRef, rs);
		}
		
		model.addAttribute("entityId", entityId);
		try {
		model.addAttribute("entityName", Class.forName(entityName).getSimpleName());
		} catch (Exception e) {}
		model.addAttribute("records", records);
		model.addAttribute("txReferences", txReferences);
		return VIEW_INTERACTION_OBJECTHISTORY;
	}
	

	
}
