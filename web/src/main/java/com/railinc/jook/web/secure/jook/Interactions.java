package com.railinc.jook.web.secure.jook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.railinc.jook.JookManagementService;
import com.railinc.jook.domain.StaticInteraction;
import com.railinc.jook.web.util.SetOfStringEditor;
import com.railinc.jook.web.util.StandardController;

@Controller
@RequestMapping("secure/jook/interactions")
public class Interactions extends StandardController {
	public static final String VIEW_INTERACTION_LIST = ".view.interactionList";
	private static final String REDIRECT_LIST = "redirect:list";
	public static final String VIEW_INTERACTION_FORM = ".view.interactionForm";
	public static final String VIEW_INTERACTION_DETAILS = ".view.interactionDetails";
	
	
	JookManagementService service = null;

	@ModelAttribute("applications")
	public List<String> applications() {
		List<String> a = new ArrayList<String>();
		a.add("EHMS");
		a.add("EMIS");
		return a;
	}

	public JookManagementService getService() {
		return service;
	}


	public void setService(JookManagementService service) {
		this.service = service;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Set.class, "applications", new SetOfStringEditor());
	}

	

	@ModelAttribute("availableTypes")
	public Map<String,String> types() {
		return StaticInteraction.TYPES;
	}
	
	
//	@RequestMapping(method=RequestMethod.GET,value="details")
//	public String showInteractionDetails(StaticInteraction interaction, Model model)  {
//		StaticInteraction productByName = service.getInteraction(interaction.getId());
//		 
//		model.addAttribute("interaction", productByName);
//
//		
//		return VIEW_INTERACTION_DETAILS;
//	}

	
	
	// show edit form
	@RequestMapping(method=RequestMethod.GET,value="update")
	public String showExistingInteractionForm(@ModelAttribute("interaction") StaticInteraction interaction, Model model)  {
		StaticInteraction productByName = service.getInteraction(interaction.getId());
		if (productByName == null) {
			productByName = new StaticInteraction();
		}
		model.addAttribute("interaction", productByName);
		return VIEW_INTERACTION_FORM;
	}



	@RequestMapping(method=RequestMethod.POST,value="update",params="_eventId_cancel")
	public String cancelUpdate(ModelMap map, HttpServletRequest r) {
		message(r,"No action has been taken");
		return REDIRECT_LIST;
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="update",params="_eventId_save")
	public String saveInteraction(@ModelAttribute("interaction") StaticInteraction value, BindingResult result, HttpServletRequest r) {
		StaticInteraction p = getService().getInteraction(value.getId());
		
		ValidationUtils.rejectIfEmpty(result, "type", "required");
		ValidationUtils.rejectIfEmpty(result, "title", "required");
		
		if (result.hasErrors()) {
			return VIEW_INTERACTION_FORM;
		}
		
		if (p != null) {
			p.setActive(value.getActive());
			p.setApplications(value.getApplications());
			p.setSecureUrl(value.getSecureUrl());
			p.setUnsecureUrl(value.getUnsecureUrl());
			p.setExcludeApplications(value.getExcludeApplications());
			p.setTitle(value.getTitle());
			p.setType(value.getType());
		} else {
			p = value;
		}
		getService().updateInteraction(p);
		message(r, String.format("Successfully saved the interaction '%s'", p.getTitle()));
		
		return REDIRECT_LIST;
	}
	
	@RequestMapping(value="update",params="_eventId_delete")
	public String deleteInteraction(@ModelAttribute("interaction") final StaticInteraction value, ModelMap map, HttpServletRequest r) {
		boolean ok = doQuietly(new WebCommand() {
			@Override
			public void doIt() {
				StaticInteraction p = getService().getInteraction(value.getId());
				getService().deleteInteraction(p);
			}
		}, r);
		if (ok) {
			message(r, "The product was deleted.");
			return REDIRECT_LIST;
		} else {
			return VIEW_INTERACTION_FORM;
		}
	}
		
	@RequestMapping(method=RequestMethod.GET,value={""})
	public String allInteractionsR(Model model) {
		return "redirect:interactions/list";
	}	
	
	@RequestMapping("")
	public String redirectLanding() {
		return "redirect:interactions/list";
	}
	@RequestMapping(method=RequestMethod.GET,value={"list"})
	public String allInteractions(Model model) {
		List<StaticInteraction> interactions = service.interactions(0, Integer.MAX_VALUE, null, true);
		model.addAttribute("interactions", interactions);

		return VIEW_INTERACTION_LIST;
	}
	

	
}
