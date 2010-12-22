package com.railinc.jook.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.railinc.fukumu.Fukumu;

public class StaticInteraction extends DomainObject {
	public static Map<String, String> TYPES;
	static {
		Map<String, String> list = new LinkedHashMap<String, String>();
		list.put("popupext", "Popup in New Window");
		list.put("popup", "Immediate Popup (use with caution)");
		list.put("popuptab", "Drawer Popup Dialog");
		list.put("tab", "Drawer");
		StaticInteraction.TYPES = Collections.unmodifiableMap(list);
	}

	Boolean active = Boolean.TRUE;
	Set<String> applications;
	Set<String> requiredRoles;

	Boolean excludeApplications = Boolean.FALSE;
	String secureUrl;
	String title;
	String type;
	String unsecureUrl;

	public Boolean getActive() {
		return active;
	}

	public Set<String> getApplications() {
		return applications;
	}

	public Boolean getExcludeApplications() {
		return excludeApplications;
	}

	public String getSecureUrl() {
		return secureUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public String getUnsecureUrl() {
		return unsecureUrl;
	}

	public String getVerboseType() {
		return TYPES.get(getType());
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

	public void setSecureUrl(String secureUrl) {
		if (secureUrl != null && secureUrl.trim().length() == 0) {
			secureUrl = null;
		}
		this.secureUrl = secureUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUnsecureUrl(String unsecureUrl) {
		if (unsecureUrl != null && unsecureUrl.trim().length() == 0) {
			unsecureUrl = null;
		}

		this.unsecureUrl = unsecureUrl;
	}

	public Set<String> getRequiredRoles() {
		return requiredRoles;
	}

	public void setRequiredRoles(Set<String> requiredRoles) {
		this.requiredRoles = requiredRoles;
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
