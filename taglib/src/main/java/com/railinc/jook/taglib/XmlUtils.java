package com.railinc.jook.taglib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlUtils {
	static final Pattern entitiesPattern = Pattern.compile("&#(\\d+);");
	public static String fixEntities(String values) {
		StringBuilder sb = new StringBuilder();
		
		Matcher matcher = entitiesPattern.matcher(values);
		int lastIndex = 0;
		while (matcher.find()) {
			int ch = Integer.parseInt(matcher.group(1));
			sb.append(values.subSequence(lastIndex, matcher.start()));
			sb.append((char) ch);
			lastIndex = matcher.end();
		}
		if (values.length() > lastIndex) {
			sb.append(values.subSequence(lastIndex, values.length()));
		}
		return sb.toString();
		
	}
	
	public static String escapeXml(String xmlTextContent) {
		String tmp = xmlTextContent;
		tmp = tmp.replaceAll("&", "&amp;");
		tmp = tmp.replaceAll(">", "&gt;");
		tmp = tmp.replaceAll("<", "&lt;");		
		return tmp;
	}

}
