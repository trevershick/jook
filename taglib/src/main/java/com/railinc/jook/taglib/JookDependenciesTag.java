package com.railinc.jook.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class JookDependenciesTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6028258429021742387L;

	private static final String scriptTag = "<script src=\"/jook/client/javascript/1.0/jookdeps.js?_j=%s&_f=%s&_m=%s\" type=\"text/javascript\"></script>";

	
	boolean jquery = true;
	boolean facebox = true;
	boolean jqueryForm = true;
	
	
	

	public boolean isJqueryForm() {
		return jqueryForm;
	}

	public void setJqueryForm(boolean jqueryForm) {
		this.jqueryForm = jqueryForm;
	}

	public boolean isJquery() {
		return jquery;
	}

	public void setJquery(boolean jquery) {
		this.jquery = jquery;
	}

	public boolean isFacebox() {
		return facebox;
	}

	public void setFacebox(boolean facebox) {
		this.facebox = facebox;
	}


	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write(String.format(scriptTag,  String.valueOf(jquery), String.valueOf(facebox), String.valueOf(jqueryForm)));
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}

}
