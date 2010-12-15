package com.railinc.jook.domain;

public class PreferenceSpec extends DomainObject {

	private String defaultValue;
	private String description;
	private PreferenceGroup group;
	private String key;
	private String name;
	private String type = "string";

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getDescription() {
		return description;
	}

	public PreferenceGroup getGroup() {
		return group;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setGroup(PreferenceGroup group) {
		this.group = group;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}
}
