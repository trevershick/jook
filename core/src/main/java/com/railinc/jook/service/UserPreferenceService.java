package com.railinc.jook.service;

import java.util.List;

import com.railinc.jook.domain.Preference;

public interface UserPreferenceService {
	/**
	 * @param applicationKey - cannot be null
	 * @param userId - cannot be null
	 * @return a list of preferences the user has specifically set or the default value
	 */
	public List<Preference> getUserPreferences(String applicationKey, String userId);
	
	/**
	 * @param applicationKey - can be null
	 * @param userId - cannot be null
	 * @return a list of preferences the user has specifically set or overridden the defaults (never null)
	 */
	public List<Preference> getUserOverrides(String applicationKey,	String userId);
	/**
	 * Obtains the defaults for an application.  This converts preference types into preference objects
	 * before returning them.
	 * @param applicationKey - cannot be null
	 * @return a list of preference objects populated with defaults from the preference specs (never null)
	 */
	public List<Preference> getApplicationDefaults(String applicationKey);

	public void updateUserPreference(String app, String user, String key,
			String value);
}
