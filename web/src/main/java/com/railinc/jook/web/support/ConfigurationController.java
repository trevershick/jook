/**
 * Copyright (c) Railinc Corporation, 2009.
 * All rights reserved.
 */
package com.railinc.jook.web.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.railinc.jook.domain.ConfigurationValue;

/**
 * @author sdtxs01
 *
 */
@Controller
@RequestMapping("secure/support/configuration")
public class ConfigurationController extends SupportControllerBaseImpl {

	public ConfigurationController() {
		System.out.println("Yay!");
	}
//	@ModelAttribute("random")
//	public String getNextRandom() {
//		return String.valueOf(random.nextLong());
//	}
//	
//	
//	@ModelAttribute("hostname")
//	public String getHostname() {
//		return this.hostname;
//	}
//	/**
//	 * @return the address
//	 */
//	@ModelAttribute("address")
//	public String getAddress() {
//		return address;
//	}


	
	
	
	
	/**
	 * Delete the speicifed key and return to the main screen.
	 * @param key
	 * @param map
	 * @return
	 */
	@RequestMapping(value="submit",params="_eventId_delete")
	public String configurationDelete(@RequestParam(value="key",required=true) String key, ModelMap map) {
		getConfigurationService().removeConfiguration(key);
		return configuration(map);
	}
	
	@RequestMapping(value="update",params="!k")
	public String configurationUpdate(ModelMap map) {
		ConfigurationValue configurationValue = new ConfigurationValue();
		map.addAttribute("configurationValue", configurationValue);
		return ".view.support.configuration.update";
	}

	@RequestMapping("update")
	public String configurationUpdate(@RequestParam(value="k",required=true) String key, ModelMap map) {
		ConfigurationValue configurationValue = getConfigurationService().getConfigurationValue(key);
		map.addAttribute("configurationValue", configurationValue);
		return ".view.support.configuration.update";
	}

	@RequestMapping(value="submit",params="_eventId_cancel")
	public String configurationCancelUpdate(ModelMap map, HttpServletRequest r) {
		message(r,"No action has been taken");
		return "redirect:list";
	}
	
	@RequestMapping(value="submit",params="_eventId_save")
	public String configurationUpdateObject(ConfigurationValue value, ModelMap map) {
		if (null == getConfigurationService().getConfigurationValue(value.getKey())) {
			getConfigurationService().createConfiguration(value.getKey(), value.getStringValue(), value.getDescription());
		} else {
			getConfigurationService().updateConfiguration(value.getKey(), value.getStringValue(), value.getDescription());
		}
		return "redirect:list";
	}

	
	@RequestMapping("list")
	public String configuration(ModelMap map) {
		map.addAttribute("configurationValues",getConfigurationService().getConfigurationValues());
		return ".view.support.configuration";
	}	
}
