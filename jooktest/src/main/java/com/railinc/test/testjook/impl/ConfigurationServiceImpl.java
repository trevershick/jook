package com.railinc.test.testjook.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.railinc.test.testjook.service.ConfigurationService;
import com.railinc.test.testjook.service.ConfigurationValue;

public class ConfigurationServiceImpl implements ConfigurationService {
	Map<String,String> configValues = new HashMap<String, String>();
	
	public void createConfiguration(String key, String value, String description) {
		if (key == null || value == null || description == null) {
			throw new IllegalArgumentException("key,value and description may not be null.");
		}
		configValues.put(key,value);
	}

	public ConfigurationValue getConfigurationValue(String key) {
		Set<String> keySet = configValues.keySet();
		String string = this.configValues.get(key);
		if (string == null) {
			return null;
		}
		ConfigurationValueImpl i = new ConfigurationValueImpl();
		i.setKey(key);
		i.setStringValue(string);
		return i;
	}

	@SuppressWarnings("unchecked")
	public List<ConfigurationValue> getConfigurationValues() {
		if (this.configValues.isEmpty()) {
			this.initialProperties();
		}
		List<ConfigurationValue> results = new ArrayList<ConfigurationValue>();
		
		Set<String> keySet = configValues.keySet();
		for (String k : keySet) {
			results.add(getConfigurationValue(k));
		}
		return results;
	}

	public void updateConfiguration(String key, String value) {
		this.configValues.put(key,value);
	}

	public void updateConfiguration(String key, String value, String description) {
		this.configValues.put(key,value);
	}

	public String getStringValue(String key) {
		ConfigurationValue configurationValue = getConfigurationValue(key);
		String tmp = null;
		if (configurationValue != null) {
			tmp = configurationValue.getStringValue();
		}
		return tmp;
	}

	public void removeConfiguration(String key) {
		configValues.remove(key);
	}

	public String getStringValue(String key, String defaultIfNull) {
		String tmp = getStringValue(key);
		if (tmp == null) {
			tmp = defaultIfNull;
		}
		return tmp;
	}
	
	
	private void initialProperties() {
	    createConfiguration("link.signout", "/sso/logout.do", "");
	    createConfiguration("link.launchpad","/rportal/web/csc","");
	    createConfiguration("link.userservices","/sso","");
	    createConfiguration("link.contactus", "/rportal/web/guest/contactus","");
   		createConfiguration("link.legalnotices","/rportal/web/guest/legal","");
		createConfiguration("link.privacy", "/rportal/web/guest/privacy", "");
		createConfiguration("link.termsofservice", "/rportal/web/guest/terms","");
		createConfiguration("sso.login.url", "/sso/logout.do","");
	}

}
