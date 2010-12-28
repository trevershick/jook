package com.railinc.jook.web.util;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;


public class RequestHelper {
	private HttpServletRequest request;

	public RequestHelper(HttpServletRequest r) {
		this.request = r;
	}
	
	public String paramsAsJsonHash() {
		Enumeration<?> parameterNames = this.request.getParameterNames();
		StringBuilder sb = new StringBuilder("{");
		boolean first=true;
		while (parameterNames.hasMoreElements()) {
			String pname = parameterNames.nextElement().toString();
			String value = request.getParameter(pname);
			if (!first)sb.append(",");
			sb.append(String.format("'%s':'%s'", pname,value));
			first=false;
		}
		sb.append("}");
		return sb.toString();
	}
	
	public String substituteParameters(String pattern) {
		String tmp = pattern;
		Enumeration<?> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String p = (String) e.nextElement();
			String v = request.getParameter(p);
			tmp = tmp.replaceAll("%" + p + "%", v);
		}
		return tmp;
	}
}
