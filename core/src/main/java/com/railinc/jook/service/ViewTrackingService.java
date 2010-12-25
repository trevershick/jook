package com.railinc.jook.service;

import java.util.List;


/**
 * this really needs to be exposed as a web service or 2. this could be useful
 * I suppose it could just be a reusable java component, but it seems like a
 * web service would be fast enough (probably)
 * @author tshick
 *
 */
public interface ViewTrackingService {
	void userJustSaw(String user, String application, String resource);
	/**
	 * returns true if the user has seen the specified resource
	 * @param user
	 * @param application
	 * @param resource
	 * @return false if user == null
	 */
	boolean hasUserSeen(String user, String application, String resource);
	void resetViewState(String application, String resource);
	void resetViewState(String application, List<? extends Object> itemIds);
	
	boolean userHasNotSeenAll(String user, String viewtrackingAppname,
			List<? extends Object> itemIds);
	void userJustSawItems(String user, String viewtrackingAppname,
			List<? extends Object> itemIds);
	List<String> whatHasUserSeen(String user,
			String viewtrackingAppname);
}
