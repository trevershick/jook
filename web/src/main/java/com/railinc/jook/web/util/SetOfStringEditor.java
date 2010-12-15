package com.railinc.jook.web.util;

import java.beans.PropertyEditorSupport;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class SetOfStringEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String arg0) throws IllegalArgumentException {
		if (arg0 != null) {
			String[] split = arg0.split(",");
			Set<String> s = new HashSet<String>();
			for (String str : split) {
				str = str.trim();
				if (StringUtils.isNotBlank(str)) {
					s.add(str);
				}
			}
			setValue(s);
		} else {
			setValue(new HashSet<String>());
		}
	}

	@Override
	public String getAsText() {
		Set<String> o = (Set<String>) getValue();
		return StringUtils.join(o, ",");
	}
}
