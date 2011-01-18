package com.railinc.test.testjook.web.global;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.railinc.test.testjook.service.ConfigurationService;

@Controller
public class HomeController {
	
	ConfigurationService configurationService;

	/**
	 * @return the configurationService
	 */
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	/**
	 * @param configurationService the configurationService to set
	 */
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@RequestMapping
	public void home(ModelMap map) {
		String stringValue = getConfigurationService().getStringValue("news.rss.url");
		if (StringUtils.isNotBlank(stringValue)) {
			map.addAttribute("newsRssUrl", stringValue);
		}
		map.addAttribute("homeFreeText", getConfigurationService().getStringValue("home.freetext", "'home.freetext' has not been set in the system configuration"));
	}
}
