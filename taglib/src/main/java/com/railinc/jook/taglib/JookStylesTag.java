package com.railinc.jook.taglib;


public class JookStylesTag extends BaseJookTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6028258429021742387L;

	private static final String scriptTag = "<link rel=\"stylesheet\" type=\"text/css\" href=\"%s/client/css/%s/jook.css?app=%s\"></script>";

	@Override
	protected Object[] getTagFormatArguments() {
		return new Object[] { getBasePath(), JOOK_CLIENT_VERSION, getApp() };
	}

	@Override
	protected String getTagFormatString() {
		return scriptTag;
	}

}
