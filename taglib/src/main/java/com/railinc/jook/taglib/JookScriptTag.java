package com.railinc.jook.taglib;


public class JookScriptTag extends BaseJookTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6028258429021742387L;

	private static final String scriptTag = "<script src=\"%s/client/javascript/%s/jook.js?app=%s\" type=\"text/javascript\"></script>";

	

	@Override
	protected Object[] getTagFormatArguments() {
		return new Object[]{getBasePath(),JOOK_CLIENT_VERSION, getApp()};
	}

	@Override
	protected String getTagFormatString() {
		return scriptTag;
	}

}
