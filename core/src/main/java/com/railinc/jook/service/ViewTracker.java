package com.railinc.jook.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewTracker {
	ViewTrackingService service;
	
	String user;
	String application;
	List<String> idsToMarkSeen = new ArrayList<String>();
	List<String> idsMarkedSeen = new ArrayList<String>(); 
	
	public ViewTracker(ViewTrackingService service) {
		this.service = service;
	}
	
	public ViewTracker(ViewTrackingService service, String app) {
		this(service);
		withApp(app);
	}
	
	public ViewTracker(ViewTrackingService service, String app, String user) {
		this(service,app);
		withUser(user);
	}
	
	public ViewTracker withApp(String app){ 
		this.application = app;
		return this;
	}
	
	public ViewTracker withUser(String user) {
		this.user = user;
		return this;
	}
	
	public ViewTracker justSawId(Object id) {
		this.idsToMarkSeen.add(String.valueOf(id));
		return this;
	}
	
	public List<String> whatHasUserSeen() {
		if (this.service != null && this.application != null && this.user != null) {
			return this.service.whatHasUserSeen(user, application);
		} else {
			return Collections.emptyList();
		}
	}
	
	public ViewTracker execute() {
		if (this.service != null && this.application != null && this.user != null) {
			this.service.userJustSawItems(user, application, idsToMarkSeen);
			this.idsMarkedSeen.addAll(this.idsToMarkSeen);
			this.idsToMarkSeen.clear();
		}
		return this;
	}

	public boolean userHasSeenAll(List<Object> keys) {
		return !userHasNotSeenAll(keys);
	}
	
	public boolean userHasNotSeenAll(List<?> keys) {
		if (this.service != null && this.application != null && this.user != null) {
			return this.service.userHasNotSeenAll(user, application, keys);
		}
		return true;
	}
}
