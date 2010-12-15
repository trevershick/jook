package com.railinc.jook.domain;

import java.util.Set;



public class JookInteractionProvider extends DomainObject {
	String name;
	String servicesJsonPath;
	Set<String> applications;
	Boolean active = Boolean.FALSE;
	
	
	
	
	public Set<String> getApplications() {
		return applications;
	}
	public void setApplications(Set<String> applications) {
		this.applications = applications;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getServicesJsonPath() {
		return servicesJsonPath;
	}
	public void setServicesJsonPath(String servicesJsonPath) {
		this.servicesJsonPath = servicesJsonPath;
	}
	

	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
