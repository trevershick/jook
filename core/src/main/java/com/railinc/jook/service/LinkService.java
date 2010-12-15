package com.railinc.jook.service;

import java.util.List;

import com.railinc.jook.domain.Link;

public interface LinkService {
	Link getLink(String key);
	List<Link> getLinks();
	
	void updateLink(String path, String url, String description);
	
	void createLink(String path, String url, String description);
	void removeLink(String key);
}