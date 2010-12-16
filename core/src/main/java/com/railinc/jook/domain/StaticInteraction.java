package com.railinc.jook.domain;

import java.util.Set;

import com.railinc.jook.interaction.JookInteraction;

public class StaticInteraction extends DomainObject implements JookInteraction {
	String type;
	String title;
	String url;
	Boolean shake = null;
	Boolean active = Boolean.TRUE;
	Boolean secured = Boolean.FALSE;
	Boolean excludeApplications = Boolean.FALSE;
	
	public Boolean getExcludeApplications() {
		return excludeApplications;
	}
	public Boolean getShake() {
		return shake;
	}
	public void setShake(Boolean shake) {
		this.shake = shake;
	}
	public void setExcludeApplications(Boolean excludeApplications) {
		this.excludeApplications = excludeApplications;
	}
	public Boolean getSecured() {
		return secured;
	}
	public void setSecured(Boolean secured) {
		this.secured = secured;
	}
	Set<String> applications;

	public Set<String> getApplications() {
		return applications;
	}
	public void setApplications(Set<String> applications) {
		this.applications = applications;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@Override
	public boolean shake() {
		return (this.shake != null) && this.shake;
	}
	
	
}
