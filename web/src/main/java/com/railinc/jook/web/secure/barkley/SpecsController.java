package com.railinc.jook.web.secure.barkley;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.railinc.jook.domain.PreferenceGroup;
import com.railinc.jook.domain.PreferenceSpec;
import com.railinc.jook.service.PreferenceService;
import com.railinc.jook.web.util.PreferenceGroupPropertyEditor;
import com.railinc.jook.web.util.StandardController;

@Controller
@RequestMapping("secure/barkley/specs")
public class SpecsController extends StandardController {

	PreferenceService preferenceService;
	

	@InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(PreferenceGroup.class, new PreferenceGroupPropertyEditor(getPreferenceService()));
    }
	
	
	@RequestMapping(value="submit",params="_eventId_delete")
	public String configurationDelete(@RequestParam(value="specId",required=true) Long specId, ModelMap map, HttpServletRequest request) {
		getPreferenceService().deleteSpec(specId);
		propagateStandardParamsToModel(request, map);
		return "redirect:list";
	}
	
	
	@ModelAttribute("groups")
	public List<?> groups() {
		return getPreferenceService().findGroups(null);
	}
	
	
	@RequestMapping(value="update",params="!specId")
	public String specUpdate(ModelMap map) {
		PreferenceSpec configurationValue = new PreferenceSpec();
		map.addAttribute("spec", configurationValue);

		return ".view.barkley.specs.update";
	}

	
	
	
	@RequestMapping("update")
	public String specUpdate(@RequestParam(value="specId",required=true) String key, ModelMap map) {
		PreferenceSpec configurationValue = getPreferenceService().getSpec(Long.valueOf(key));
		map.addAttribute("spec", configurationValue);
		return ".view.barkley.specs.update";
	}

	@RequestMapping(value="submit",params="_eventId_cancel")
	public String configurationCancelUpdate(ModelMap map, HttpServletRequest r) {
		propagateStandardParamsToModel(r, map);
		message(r,"No action has been taken");
		return "redirect:list";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="submit",params="_eventId_save")
	public String specUpdateObject(@ModelAttribute("spec") PreferenceSpec value, BindingResult result, ModelMap map, HttpServletRequest request) {
		propagateStandardParamsToModel(request, map);

		
		ValidationUtils.rejectIfEmpty(result, "key", "barkley.required");
		ValidationUtils.rejectIfEmpty(result, "name", "barkley.required");
		ValidationUtils.rejectIfEmpty(result, "group", "barkley.required");
		ValidationUtils.rejectIfEmpty(result, "defaultValue", "barkley.required");
		if (result.hasErrors()) {
			return ".view.barkley.specs.update";
		}
		try {
			getPreferenceService().saveSpec(value);
		} catch (DataIntegrityViolationException cve) {
			map.addAttribute("spec", value);
			map.addAttribute("groups", getPreferenceService().findGroups(null));
			result.rejectValue("key", "data.integrity.spec.key", cve.getMessage());
			return ".view.barkley.specs.update"; 
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

	
	
	@RequestMapping("list")
	public String list(ModelMap model, @RequestParam(required=false,value="q") String queryString) {
		List<PreferenceSpec> findSpecs = getPreferenceService().findSpecs(queryString);
		model.addAttribute("specs", findSpecs);
		return ".view.barkley.specs.list";
	}
	
	
	
	

	public PreferenceService getPreferenceService() {
		return preferenceService;
	}

	public void setPreferenceService(PreferenceService preferenceService) {
		this.preferenceService = preferenceService;
	}
}
