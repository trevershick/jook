package com.railinc.test.testjook.service;

import java.util.List;

public interface ConfigurationService {
	ConfigurationValue getConfigurationValue(String key);
	String getStringValue(String key, String defaultIfNull);
	String getStringValue(String key);
	List<ConfigurationValue> getConfigurationValues();
	
	void updateConfiguration(String key, String value);
	void updateConfiguration(String key, String value,String description);
	
	void createConfiguration(String key, String value, String description);
	void removeConfiguration(String key);
}