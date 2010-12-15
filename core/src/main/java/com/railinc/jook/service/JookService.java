package com.railinc.jook.service;

import java.util.List;

import com.railinc.jook.domain.JookInteractionProvider;

public interface JookService {
	/**
	 * lookup the JSON providers for a given module id
	 * @param ssoModuleId
	 * @return
	 */
	public List<JookInteractionProvider> providersForModuleId(String ssoModuleId);
	
}
