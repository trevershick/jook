package com.railinc.test.testjook.impl;

import com.railinc.test.testjook.service.ConfigurationValue;



public class ConfigurationValueImpl implements ConfigurationValue, java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5213611472105807981L;
	String key;
	String stringValue;
	String description;
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the stringValue
	 */
	public String getStringValue() {
		return stringValue;
	}
	/**
	 * @param stringValue the stringValue to set
	 */
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
}
