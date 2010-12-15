package com.railinc.jook;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.dao.InvalidDataAccessResourceUsageException;

import com.railinc.jook.service.ConfigurationService;

public class DbPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	ConfigurationService configurationService;

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#resolvePlaceholder(java.lang.String, java.util.Properties)
	 */
	@Override
	protected String resolvePlaceholder(String placeholder, Properties props) {
		String key = placeholder;
		String value = null;
		
		if (configurationService != null && ((value = configurationService.getStringValue(key)) != null)) {
			return value;
		}
		return super.resolvePlaceholder(placeholder, props);
	}

	/**
	 * @return the service
	 */
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	/**
	 * @param service the service to set
	 */
	public void setConfigurationService(ConfigurationService service) {
		this.configurationService = service;
		if (this.configurationService != null) {
			try {
				this.configurationService.getConfigurationValues();
			} catch (InvalidDataAccessResourceUsageException e) {
				this.configurationService = null;
			}
		}
	}
	
}
