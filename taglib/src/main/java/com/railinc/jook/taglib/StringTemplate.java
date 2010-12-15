package com.railinc.jook.taglib;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTemplate {
	String template = null;
	private Map<String, Object> arguments = new HashMap<String, Object>();
	private boolean escapeXml;

	public StringTemplate(String template) {
		if (template == null) {
			throw new IllegalArgumentException("template cannot be null");
		}
		this.template = template;
	}

	public StringTemplate withArgument(String name, Object value) {
		arguments.put(name, value);
		return this;
	}

	public StringTemplate withArguments(Map<String, Object> arguments) {
		if (arguments == null) {
			arguments = new HashMap<String, Object>();
		}
		this.arguments = arguments;
		return this;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(template.length());
		Pattern compile = Pattern.compile("%%(\\w+)%%");
		Matcher matcher = compile.matcher(template);
		while (matcher.find()) {
			String var = matcher.group(1);
			Object resolved = arguments.get(var);
			if (resolved == null) {
				resolved = "";
//				throw new IllegalArgumentException(var
//						+ " has not been supplied in " + arguments);
			}
			String resolvedString = String.valueOf(resolved);
			resolvedString = escapeXml ? XmlUtils.escapeXml(resolvedString)
					: resolvedString;
			matcher.appendReplacement(sb, Matcher.quoteReplacement(resolvedString));
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public StringTemplate escapeXml() {
		this.escapeXml = true;
		return this;
	}

}
