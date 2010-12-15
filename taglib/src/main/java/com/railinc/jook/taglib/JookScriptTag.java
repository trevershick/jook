package com.railinc.jook.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.railinc.sso.rt.LoggedUser;
import com.railinc.sso.rt.UserService;

public class JookScriptTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6028258429021742387L;

	private static final String scriptTag = "<script src=\"/jook/client/javascript/1.0/jook.js?ssoUser=%s&app=%s\" type=\"text/javascript\"></script>";

	String app;
	String ssoUser;

	
	



	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getSsoUser() {
		if (null == ssoUser) {
			LoggedUser loggedUser = UserService
					.getLoggedUser((HttpServletRequest) this.pageContext
							.getRequest());
			this.ssoUser = loggedUser.getUserId();
		}
		return ssoUser;
	}

	public void setSsoUser(String ssoUser) {
		this.ssoUser = ssoUser;
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write(String.format(scriptTag, getSsoUser(), getApp()));
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}

}
