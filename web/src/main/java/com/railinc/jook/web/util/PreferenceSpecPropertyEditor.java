package com.railinc.jook.web.util;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;

import com.railinc.jook.domain.PreferenceSpec;
import com.railinc.jook.service.PreferenceService;

public class PreferenceSpecPropertyEditor extends PropertyEditorSupport {
	PreferenceService service;

	public PreferenceSpecPropertyEditor(PreferenceService s) {
		this.service = s;
	}
	public PreferenceService getService() {
		return service;
	}

	@Override
	public void setAsText(String arg0) throws IllegalArgumentException {
		if (StringUtils.isNumeric(arg0)) {
			setValue(getService().getSpec(Long.valueOf(arg0)));
		} else {
			setValue(null);
		}
	}

	@Override
	public String getAsText() {
		Object o = getValue();
		if (o instanceof PreferenceSpec) {
			PreferenceSpec g = (PreferenceSpec) o;
			if (g != null) {
				return String.valueOf(g.getId());
			}
		}
		return "";
	}
	
	
}
