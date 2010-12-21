package com.railinc.jook.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.railinc.fukumu.Fukumu;

public class StaticInteraction extends DomainObject {

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

	public static Map<String, String> TYPES;
	String type;
	String title;
	String unsecureUrl;
	String secureUrl;
	Boolean active = Boolean.TRUE;
	Boolean excludeApplications = Boolean.FALSE;
	
	public Boolean getExcludeApplications() {
		return excludeApplications;
	}


	public void setExcludeApplications(Boolean excludeApplications) {
		this.excludeApplications = excludeApplications;
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
	public String getVerboseType() {
		return TYPES.get(getType());
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	
	static {
		Map<String,String> list = new LinkedHashMap<String,String>();
		list.put("popupext", "Popup in New Window");
		list.put("popup", "Immediate Popup (use with caution)");
		list.put("popuptab", "Drawer Popup Dialog");
		list.put("tab", "Drawer");
		StaticInteraction.TYPES = Collections.unmodifiableMap(list);
	}


	public boolean isAvailableForApp(String moduleId) {
		return new Fukumu<String>(getApplications()).includeByDefault(!this.getExcludeApplications()).includes(moduleId);
	}

}
