package com.railinc.jook.taglib;




public class JookDependenciesTag extends BaseJookTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6028258429021742387L;

	private static final String SCRIPT_TAG = "<script src=\"%s/client/javascript/%s/jookdeps.js?app=%s\" type=\"text/javascript\"></script>";

	

	protected Object[] getTagFormatArguments() {
		return new Object[]{ getBasePath(), JOOK_CLIENT_VERSION, getApp()};
	}

	protected String getTagFormatString() {
		return SCRIPT_TAG;
	}

}
