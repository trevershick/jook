package com.railinc.jook.web.secure.barkley;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.railinc.jook.domain.Preference;
import com.railinc.jook.service.PreferenceService;
import com.railinc.jook.service.UserPreferenceService;


@Controller
@RequestMapping("secure/barkley/test")
public class TestController {
	
	private PreferenceService preferenceService;
	
	private UserPreferenceService service;
	
	@ModelAttribute("applications")
	public List<String> getApplicationKeys() {
		return getPreferenceService().getApplicationKeys();
	}
	
	public PreferenceService getPreferenceService() {
		return preferenceService;
	}
	
	
	public UserPreferenceService getService() {
		return service;
	}

	@RequestMapping("pdefaults")
	public String pdefaults(ModelMap model, @RequestParam(value="app",required=false) String appKey) {
		if (StringUtils.isNotBlank(appKey)) {
			List<Preference> applicationDefaults = getService().getApplicationDefaults(appKey);
			model.addAttribute("defaults", applicationDefaults);
		}
		return ".view.barkley.prefs.pdefaults";
	}
	public void setPreferenceService(PreferenceService preferenceService) {
		this.preferenceService = preferenceService;
	}
	public void setService(UserPreferenceService service) {
		this.service = service;
	}
	@RequestMapping("uoverrides")
	public String uoverrides(ModelMap model, @RequestParam(value="app",required=false) String appKey, @RequestParam(value="user",required=false) String userId) {
		if (StringUtils.isNotBlank(userId)) {
			List<Preference> overrides = getService().getUserOverrides(appKey, userId);
			model.addAttribute("overrides", overrides);
		}
		return ".view.barkley.prefs.uoverrides";
	}




	@RequestMapping("upreferences")
	public String upreferences(ModelMap model, @RequestParam(value="app",required=false) String appKey, @RequestParam(value="user",required=false) String userId) {
		if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(appKey)) {
			List<Preference> preferences = getService().getUserPreferences(appKey, userId);

			model.addAttribute("preferences", preferences);
		}
		return ".view.barkley.prefs.upreferences";
	}
}
