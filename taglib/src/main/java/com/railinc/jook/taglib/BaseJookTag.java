package com.railinc.jook.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.railinc.sso.rt.UserService;

public abstract class BaseJookTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4160127169506587670L;
	
	public static final String JOOK_CLIENT_VERSION = "1.0";
	private String app;
	private String jookContext = "/jook";

	public String getJookContext() {
		return jookContext;
	}

	public void setJookContext(String jookContext) {
		if (!jookContext.startsWith("/")) {
			throw new IllegalArgumentException("The jookContext must start with a '/', valid examples '/test' or '/jook'");
		}
		if (jookContext.endsWith("/")) {
			throw new IllegalArgumentException("The jookContext not end with a '/', valid examples '/test' or '/jook'");
		}
		this.jookContext = jookContext;
	}

	protected String getBasePath() {
		return new StringBuilder(getJookContext()).append(isSecured() ? "/secured" : "").toString();
	}

	public boolean isSecured() {
		return UserService.getInstance().isAuthenticated((HttpServletRequest) pageContext.getRequest());
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write(String.format(getTagFormatString(), getTagFormatArguments()));
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	protected abstract Object[] getTagFormatArguments();

	protected abstract String getTagFormatString();

	public int doEndTag() {
		return EVAL_PAGE;
	}

}
