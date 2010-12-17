package com.railinc.jook.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.railinc.sso.rt.LoggedUser;
import com.railinc.sso.rt.UserService;

public class JookStylesTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6028258429021742387L;

	private static final String scriptTag = "<link rel=\"stylesheet\" type=\"text/css\" href=\"/jook/client/css/1.0/jook.css?su=%s&app=%s&_k=%s&_f=%s\"></script>";

	String app;
	String ssoUser;
	boolean jook = true;
	public boolean isJook() {
		return jook;
	}

	public void setJook(boolean jook) {
		this.jook = jook;
	}

	boolean facebox = true;
	
	
	

	public boolean isFacebox() {
		return facebox;
	}

	public void setFacebox(boolean facebox) {
		this.facebox = facebox;
	}



	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getSsoUser() {
		if (null == ssoUser) {
			LoggedUser loggedUser = UserService.getLoggedUser((HttpServletRequest) this.pageContext.getRequest());
			this.ssoUser = loggedUser.getUserId();
		}
		return ssoUser;
	}

	public void setSsoUser(String ssoUser) {
		this.ssoUser = ssoUser;
	}

	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write(String.format(scriptTag, getSsoUser(), getApp(), String.valueOf(jook), String.valueOf(facebox)));
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}
	
}
