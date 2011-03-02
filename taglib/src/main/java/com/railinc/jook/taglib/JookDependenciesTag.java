package com.railinc.jook.taglib;




public class JookDependenciesTag extends BaseJookTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6028258429021742387L;

	private static final String SCRIPT_TAG = "<script src=\"%s/client/javascript/%s/jookdeps.js?app=%s&_j=%s&_u=%s&_m=%s&_f=%s&_g=%s\" type=\"text/javascript\"></script>";

	//j u m f g
	boolean jquery;
	boolean jqueryUi;
	boolean jqueryForm;
	boolean facebox;
	boolean jgrowl;


	
	
	public boolean isJquery() {
		return jquery;
	}

	public void setJquery(boolean jquery) {
		this.jquery = jquery;
	}

	public boolean isJqueryUi() {
		return jqueryUi;
	}

	public void setJqueryUi(boolean jqueryUi) {
		this.jqueryUi = jqueryUi;
	}

	public boolean isJqueryForm() {
		return jqueryForm;
	}

	public void setJqueryForm(boolean jqueryForm) {
		this.jqueryForm = jqueryForm;
	}

	public boolean isFacebox() {
		return facebox;
	}

	public void setFacebox(boolean facebox) {
		this.facebox = facebox;
	}

	public boolean isJgrowl() {
		return jgrowl;
	}

	public void setJgrowl(boolean jgrowl) {
		this.jgrowl = jgrowl;
	}

	protected Object[] getTagFormatArguments() {
		return new Object[]{ getBasePath(), JOOK_CLIENT_VERSION, getApp(), jquery,jqueryUi,jqueryForm,facebox,jgrowl};
	}

	protected String getTagFormatString() {
		return SCRIPT_TAG;
	}

}
