package com.railinc.jook.web.secure.barkley;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.railinc.jook.domain.PreferenceGroup;
import com.railinc.jook.service.PreferenceService;
import com.railinc.jook.web.util.StandardController;

@Controller
@RequestMapping("secure/barkley/groups")
public class GroupsController extends StandardController {

	PreferenceService preferenceService;
	



	@RequestMapping("list")
	public String list(ModelMap model, @RequestParam(required=false,value="q") String queryString) {
		List<PreferenceGroup> findGroups = getPreferenceService().findGroups(queryString);
		model.addAttribute("groups", findGroups);
		return ".view.barkley.groups.list";
	}
	
	

	@RequestMapping(value="submit",params="_eventId_delete")
	public String configurationDelete(@RequestParam(value="groupId",required=true) Long groupId, ModelMap map, HttpServletRequest request) {
		getPreferenceService().deleteGroup(groupId);
		propagateStandardParamsToModel(request, map);
		return "redirect:list";
	}
	
	
	
	
	
	@RequestMapping(value="update",params="!groupId")
	public String groupUpdate(ModelMap map) {
		PreferenceGroup configurationValue = new PreferenceGroup();
		map.addAttribute("group", configurationValue);
		return ".view.barkley.groups.update";
	}

	@RequestMapping("update")
	public String groupUpdate(@RequestParam(value="groupId",required=true) String key, ModelMap map) {
		PreferenceGroup configurationValue = getPreferenceService().getGroup(Long.valueOf(key));
		map.addAttribute("group", configurationValue);
		return ".view.barkley.groups.update";
	}

	@RequestMapping(value="submit",params="_eventId_cancel")
	public String configurationCancelUpdate(ModelMap map, HttpServletRequest request) {
		propagateStandardParamsToModel(request, map);
		message(request,"No action has been taken");
		return "redirect:list";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="submit",params="_eventId_save")
	public String groupUpdateObject(@ModelAttribute("group") PreferenceGroup value, BindingResult result, ModelMap map, HttpServletRequest request) {
		propagateStandardParamsToModel(request, map);
		ValidationUtils.rejectIfEmpty(result, "key", "barkley.required");
		ValidationUtils.rejectIfEmpty(result, "name", "barkley.required");
		ValidationUtils.rejectIfEmpty(result, "applicationKey", "barkley.required");
		if (result.hasErrors()) {
			return ".view.barkley.groups.update";
		}
		try {
			getPreferenceService().saveGroup(value);
		} catch (DataIntegrityViolationException cve) {
			map.addAttribute("group", value);
			result.rejectValue("key", "data.integrity.group.key.duplicated");
			return ".view.barkley.groups.update"; 
		}
		return "redirect:list";
	}

	/**
	 * This method copies the 'standard parameters' to the model so that
	 * when redirects occur, those param values will be in th eredirect url
	 * Current List : 
	 * <ul>
	 * <li>q - Query String </li>
	 * </ul>
	 * @param request
	 * @param map
	 */
	private void propagateStandardParamsToModel(HttpServletRequest request, ModelMap map) {
		if (StringUtils.isNotBlank(request.getParameter("q"))) {
			map.addAttribute("q", request.getParameter("q"));
		}
	}

	
	
	
	

	public PreferenceService getPreferenceService() {
		return preferenceService;
	}

	public void setPreferenceService(PreferenceService preferenceService) {
		this.preferenceService = preferenceService;
	}
}
