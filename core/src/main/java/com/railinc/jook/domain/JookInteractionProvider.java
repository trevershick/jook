package com.railinc.jook.domain;

import java.util.Set;

import com.railinc.fukumu.Fukumu;

public class JookInteractionProvider extends DomainObject {
	Boolean active = Boolean.FALSE;
	Boolean excludeApplications = Boolean.FALSE;
	Set<String> applications;
	Set<String> requiredRoles;

	String name;
	String secureUrl;
	String unsecureUrl;

	public Boolean getActive() {
		return active;
	}

	public Set<String> getApplications() {
		return applications;
	}

	public Boolean getExcludeApplications() {
		if (excludeApplications == null) {
			excludeApplications = Boolean.FALSE;
		}
		return excludeApplications;
	}

	public String getName() {
		return name;
	}

	public Set<String> getRequiredRoles() {
		return requiredRoles;
	}

	public String getSecureUrl() {
		return secureUrl;
	}

	public String getUnsecureUrl() {
		return unsecureUrl;
	}

	public boolean isAvailableForApp(String moduleId) {
		return new Fukumu<String>(getApplications()).includeByDefault(
				!this.getExcludeApplications()).includes(moduleId);
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setApplications(Set<String> applications) {
		this.applications = applications;
	}

	public void setExcludeApplications(Boolean excludeApplications) {
		this.excludeApplications = excludeApplications;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRequiredRoles(Set<String> requiredRoles) {
		this.requiredRoles = requiredRoles;
	}

	public void setSecureUrl(String secureUrl) {
		if (secureUrl != null && secureUrl.trim().length() == 0) {
			secureUrl = null;
		}
		this.secureUrl = secureUrl;
	}

	public void setUnsecureUrl(String unsecureUrl) {
		if (unsecureUrl != null && unsecureUrl.trim().length() == 0) {
			unsecureUrl = null;
		}

		this.unsecureUrl = unsecureUrl;
	}
	public boolean isAvailableToUser(RoleInterrogator i) {
		if (0 == this.requiredRoles.size()) {
			return true;
		}
		for (String role : this.requiredRoles) {
			if (i.hasRole(role)) {
				return true;
			}
		}
		return false;
	}
	public static interface RoleInterrogator {
		boolean hasRole(String role);
	}

}
