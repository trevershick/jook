package com.railinc.jook.service;

import java.util.List;

import com.railinc.jook.domain.ConfigurationValue;

public interface ConfigurationService {
	ConfigurationValue getConfigurationValue(String key);
	String getStringValue(String key, String defaultIfNull);
	String getStringValue(String key);
	List<ConfigurationValue> getConfigurationValues();
	List<ConfigurationValue> getConfigurationValues(String keyStartsWith);
	
	void updateConfiguration(String key, String value);
	void updateConfiguration(String key, String value,String description);
	
	void createConfiguration(String key, String value, String description);
	void removeConfiguration(String key);
}