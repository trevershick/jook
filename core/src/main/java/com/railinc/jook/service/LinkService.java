package com.railinc.jook.service;

import java.util.List;

import com.railinc.jook.domain.Link;

public interface LinkService {
	Link getLink(String path);
	Link getLinkByUser(String remoteUser, String path);

	List<Link> getLinks();
	List<Link> getLinksByUser(String remoteUser);
	
	void updateLink(String path, String url, String description, String user);
	
	void createLink(String path, String url, String description, String user);
	void removeLink(String key);
}