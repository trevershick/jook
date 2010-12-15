package com.railinc.jook.sso;

import java.util.List;

public interface SSOService {
	List<Resource> getAppsForUser(String userId);
	List<Resource> getAllApplications();
}