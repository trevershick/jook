package com.railinc.jook.service.impl;

import static com.railinc.jook.ExceptionUtils.argumentCantBeNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.railinc.jook.domain.DomainObject;
import com.railinc.jook.domain.Preference;
import com.railinc.jook.domain.PreferenceSpec;
import com.railinc.jook.service.UserPreferenceService;

public class UserPreferenceServiceImpl extends BaseServiceImpl<Preference> implements UserPreferenceService {

	public List<Preference> getUserPreferences(String applicationKey, String userId) {
		argumentCantBeNull("applicationKey", applicationKey);
		argumentCantBeNull("userId", userId);

		// obtain the user overrides, then load the defaults for the app except for the overrides we already have
		DetachedCriteria c1 = DetachedCriteria.forClass(Preference.class);
		c1.add(Restrictions.eq("userId", userId));
		c1.createAlias("specification", "s").createAlias("specification.group", "g").add(Restrictions.eq("g.applicationKey", applicationKey));

		List<Preference> preferences = list(Preference.class, c1);
		List<Long> specsAlreadyLoaded = new ArrayList<Long>();
		for (Preference o : preferences) {
			specsAlreadyLoaded.add(o.getSpecification().getId());
		}
		
		
		

		
		
		c1 = DetachedCriteria.forClass(PreferenceSpec.class);
		c1.createAlias("group", "g");
		c1.add(Restrictions.eq("g.applicationKey", applicationKey));
		if (specsAlreadyLoaded.size() > 0) {
			c1.add(Restrictions.not(Restrictions.in("id", specsAlreadyLoaded)));
		}
		
		List<PreferenceSpec> objs = list(PreferenceSpec.class, c1);
		
		
		for (PreferenceSpec ps : objs) {
			Preference p = new Preference();
			p.setSpecification(ps);
			p.setUserId(null);
			p.setValue(ps.getDefaultValue());
			preferences.add(p);
		}

		
		
		
		
		
		return preferences;
	}

	
	
	public List<Preference> getUserOverrides(String applicationKey, String userId) {
		argumentCantBeNull("userId", userId);

		DetachedCriteria c1 = DetachedCriteria.forClass(domainClass());
		c1.add(Restrictions.eq("userId", userId));
		if (StringUtils.isNotBlank(applicationKey)) {
			c1.createAlias("specification", "s").createAlias("specification.group", "g").add(Restrictions.eq("g.applicationKey", applicationKey));
		}
		List<Preference> objs = list(c1);
		return objs;
	}
	
	
	public List<Preference> getApplicationDefaults(String applicationKey) {
		argumentCantBeNull("applicationKey", applicationKey);

		DetachedCriteria c1 = DetachedCriteria.forClass(PreferenceSpec.class);
		c1.createAlias("group", "g");
		c1.add(Restrictions.eq("g.applicationKey", applicationKey));
		
		List<PreferenceSpec> objs = list(PreferenceSpec.class, c1);
		
		List<Preference> defaults = new ArrayList<Preference>(objs.size());
		
		for (PreferenceSpec ps : objs) {
			Preference p = new Preference();
			p.setSpecification(ps);
			p.setUserId(null);
			p.setValue(ps.getDefaultValue());
			defaults.add(p);
		}
		return defaults;
	}



	@Override
	protected Class<? extends DomainObject> domainClass() {
		return Preference.class;
	}



	@Override
	public void updateUserPreference(String app, String user, String key,
			String value) {
		List<Preference> userPreferences = getUserPreferences(app, user);
		for (Preference p : userPreferences) {
			if (p.getSpecification().getKey().equals(key)) {
				p.setValue(value);
				if (p.getId() == null) {
					p.setCreated(new Date());
					p.setCreator(user);
					p.setUserId(user);
				}
				p.setLastModified(new Date());
				p.setLastModifier(user);
				getHibernateTemplate().save(p);
			}
		}
	}

}
