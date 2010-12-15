package com.railinc.jook.web.util;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;

import com.railinc.jook.domain.PreferenceGroup;
import com.railinc.jook.service.PreferenceService;

public class PreferenceGroupPropertyEditor extends PropertyEditorSupport {
	PreferenceService service;

	public PreferenceGroupPropertyEditor(PreferenceService s) {
		this.service = s;
	}
	public PreferenceService getService() {
		return service;
	}

	@Override
	public void setAsText(String arg0) throws IllegalArgumentException {
		if (StringUtils.isNumeric(arg0)) {
			setValue(getService().getGroup(Long.valueOf(arg0)));
		} else {
			setValue(null);
		}
	}

	@Override
	public String getAsText() {
		Object o = getValue();
		if (o instanceof PreferenceGroup) {
			PreferenceGroup g = (PreferenceGroup) o;
			if (g != null) {
				return String.valueOf(g.getId());
			}
		}
		return "";
	}
	
	
}
