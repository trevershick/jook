package com.railinc.jook.domain;

import java.util.Set;

import com.railinc.fukumu.Fukumu;



public class JookInteractionProvider extends DomainObject {
	String name;
	
	Boolean excludeApplications = Boolean.FALSE;
	public Boolean getExcludeApplications() {
		if (excludeApplications == null) {
			excludeApplications = Boolean.FALSE;
		}
		return excludeApplications;
	}
	public void setExcludeApplications(Boolean excludeApplications) {
		this.excludeApplications = excludeApplications;
	}

	Set<String> applications;
	Boolean active = Boolean.FALSE;

	String secureUrl;
	String unsecureUrl;
	


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


	

	public String getSecureUrl() {
		return secureUrl;
	}
	public void setSecureUrl(String secureUrl) {
		if(secureUrl != null && secureUrl.trim().length() == 0) {
			secureUrl = null;
		}
		this.secureUrl = secureUrl;
	}
	public String getUnsecureUrl() {
		return unsecureUrl;
	}
	public void setUnsecureUrl(String unsecureUrl) {
		if(unsecureUrl != null && unsecureUrl.trim().length() == 0) {
			unsecureUrl = null;
		}

		this.unsecureUrl = unsecureUrl;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	public boolean isAvailableForApp(String moduleId) {
		return new Fukumu<String>(getApplications()).includeByDefault(!this.getExcludeApplications()).includes(moduleId);
	}

}
