package com.railinc.jook.web.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;

public class StandardController {

	protected interface WebCommand {
		void doIt();
	}
	protected boolean doQuietly(WebCommand c, HttpServletRequest request) {
		try {
			c.doIt();
			return true;
		} catch (Exception e) {
			error(request, e.toString());
			return false;
		}
	}
	
	public void message(HttpServletRequest request, String message) {
		flashMessage(request, "messages", message);
	}
	public void warning(HttpServletRequest request, String message) {
		flashMessage(request, "warnings", message);
	}
	public void error(HttpServletRequest request, String message) {
		flashMessage(request, "errors", message);
	}
	
	
	protected void flashMessage(HttpServletRequest request, String type, String message) {
		@SuppressWarnings("unchecked")
		List<String> attribute = (List<String>) request.getSession().getAttribute(type);
		if (null == attribute) {
			List<String> arrayList = new ArrayList<String>();
			arrayList.add(message);
			request.getSession().setAttribute(type, arrayList);
		} else {
			attribute.add(message);
			request.getSession().setAttribute(type, attribute);
		}
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("messages")
	public List<String> messages(HttpServletRequest r) {
		return (List<String>) getFlashScope(r,"messages");
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("errors")
	public List<String> errors(HttpServletRequest r) {
		return (List<String>) getFlashScope(r,"errors");
		
	}
	
	@SuppressWarnings("unchecked")
	@ModelAttribute("warnings")
	public List<String> warnings(HttpServletRequest r) {
		return (List<String>) getFlashScope(r,"warnings");
	}
	
	private Object getFlashScope(HttpServletRequest r, String objname) {
		Object attribute = r.getSession().getAttribute(objname);
		r.getSession().removeAttribute(objname);
		return attribute;
		
	}
}
