package com.railinc.jook.domain;

import java.util.Date;
/**
 * the last user view is a way for the app to store
 * the last time a user viewed the 'content'. In general, these
 * are used by interaction providers to determine if they should
 * shake the user or not.
 * 
 * @author tshick
 *
 */
public class LastUserView extends DomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -822470203500087179L;
	
	public LastUserView(){}
	public LastUserView(String user, String app, String resource) {
		this.user = user;
		this.name = resource;
		this.app = app;
		this.lastViewed = new Date();
	}
	String app;
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	String name;
	String user;
	Date lastViewed;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getLastViewed() {
		return lastViewed;
	}
	public void setLastViewed(Date lastViewed) {
		this.lastViewed = lastViewed;
	}



}
