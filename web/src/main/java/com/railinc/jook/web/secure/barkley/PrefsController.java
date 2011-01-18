package com.railinc.jook.web.secure.barkley;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.railinc.jook.domain.Preference;
import com.railinc.jook.domain.PreferenceSpec;
import com.railinc.jook.service.PreferenceService;
import com.railinc.jook.web.util.PreferenceSpecPropertyEditor;
import com.railinc.jook.web.util.StandardController;

@Controller
@RequestMapping("secure/barkley/prefs")
public class PrefsController extends StandardController {

	PreferenceService preferenceService;
	

	@InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(PreferenceSpec.class, new PreferenceSpecPropertyEditor(getPreferenceService()));
    }
	
	
	@RequestMapping(value="submit",params="_eventId_delete")
	public String configurationDelete(@RequestParam(value="prefId",required=true) Long preferenceId, ModelMap map, HttpServletRequest request) {
		getPreferenceService().deletePreference(preferenceId);
		propagateStandardParamsToModel(request, map);
		return "redirect:list";
	}
	
	
	
	
	
	@RequestMapping(value="update",params="!prefId")
	public String prefUpdate(ModelMap map) {
		Preference configurationValue = new Preference();
		map.addAttribute("pref", configurationValue);
		map.addAttribute("specs", getPreferenceService().findSpecs(null));

		return ".view.barkley.prefs.update";
	}

	
	
	
	@RequestMapping("update")
	public String prefUpdate(@RequestParam(value="prefId",required=true) String key, ModelMap map) {
		Preference configurationValue = getPreferenceService().getPreference(Long.valueOf(key));
		map.addAttribute("pref", configurationValue);
		map.addAttribute("specs", getPreferenceService().findSpecs(null));
		return ".view.barkley.prefs.update";
	}

	@RequestMapping(value="submit",params="_eventId_cancel")
	public String configurationCancelUpdate(ModelMap map, HttpServletRequest r) {
		propagateStandardParamsToModel(r, map);
		message(r,"No action has been taken");
		return "redirect:list";
	}
	
	@RequestMapping(value="submit",params="_eventId_save")
	public String prefUpdateObject(@ModelAttribute("pref") Preference value, BindingResult result, ModelMap map, HttpServletRequest request) {
		propagateStandardParamsToModel(request, map);

		try {
			getPreferenceService().savePreference(value);
			map.addAttribute("message", "admin.updatepref.updated");
		} catch (DataIntegrityViolationException cve) {
			map.addAttribute("pref", value);
			map.addAttribute("specs", getPreferenceService().findSpecs(null));
			result.reject("data.integrity.error", cve.getMessage());
			//result.rejectValue("key", "data.integrity.pref.key", cve.getMessage());
			return ".view.barkley.prefs.update"; 
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
		List<Preference> findPrefs = StringUtils.isBlank(queryString) ?
				new ArrayList<Preference>(0) : 
				getPreferenceService().findPreferences(queryString);
		model.addAttribute("prefs", findPrefs);
		return ".view.barkley.prefs.list";
	}
	
	
	public PreferenceService getPreferenceService() {
		return preferenceService;
	}

	public void setPreferenceService(PreferenceService preferenceService) {
		this.preferenceService = preferenceService;
	}
}
