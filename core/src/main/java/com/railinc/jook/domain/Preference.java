package com.railinc.jook.domain;

public class Preference extends DomainObject {
	private PreferenceSpec specification;
	private String userId;
	private String value;
	
	
	public PreferenceSpec getSpecification() {
		return specification;
	}
	
	public String getUserId() {
		return userId;
	}
	public String getValue() {
		return value;
	}

	public void setSpecification(PreferenceSpec specification) {
		this.specification = specification;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
