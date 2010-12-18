package com.railinc.jook.service;



public interface ViewTrackingService {
	void userJustSaw(String user, String application, String resource);
	boolean hasUserSeen(String user, String application, String resource);
	void resetViewState(String application, String resource);
}
