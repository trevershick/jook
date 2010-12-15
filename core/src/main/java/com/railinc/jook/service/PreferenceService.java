package com.railinc.jook.service;

import java.util.List;

import com.railinc.jook.domain.Preference;
import com.railinc.jook.domain.PreferenceGroup;
import com.railinc.jook.domain.PreferenceSpec;

public interface PreferenceService {
	
	List<PreferenceGroup> findGroups(String groupKey);
	PreferenceGroup getGroup(Long valueOf);
	PreferenceGroup saveGroup(PreferenceGroup value);
	void deleteGroup(Long groupId);

	
	List<PreferenceSpec> findSpecs(String queryString);
	PreferenceSpec saveSpec(PreferenceSpec value);
	PreferenceSpec getSpec(Long specId);
	void deleteSpec(Long specId);
	
	
	List<Preference> findPreferences(String queryString);
	Preference savePreference(Preference value);
	Preference getPreference(Long preferenceId);
	void deletePreference(Long preferenceId);
	List<String> getApplicationKeys();
}
