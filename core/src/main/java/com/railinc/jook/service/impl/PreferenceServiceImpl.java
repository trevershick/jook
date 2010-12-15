package com.railinc.jook.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.railinc.jook.domain.DomainObject;
import com.railinc.jook.domain.Preference;
import com.railinc.jook.domain.PreferenceGroup;
import com.railinc.jook.domain.PreferenceSpec;
import com.railinc.jook.service.PreferenceService;

public class PreferenceServiceImpl extends BaseServiceImpl<Preference> implements PreferenceService {
	
	

	public PreferenceSpec saveSpec(PreferenceSpec group) {
		getHibernateTemplate().saveOrUpdate(group);
		return group;
	}
	public PreferenceGroup saveGroup(PreferenceGroup group) {
		
		getHibernateTemplate().saveOrUpdate(group);
		return group;
	}
	public Preference savePreference(Preference p) {
		getHibernateTemplate().saveOrUpdate(p);
		return p;
	}
	
	
	public Criterion any(Criterion ... c) {
		if (c == null) {
			return null;
		}
		if (c.length == 1) {
			return c[0];
		}
		Criterion or = null;
		for (Criterion a : c) {
			if (or == null) {
				or = a;
			} else {
				or = Restrictions.or(or, a);
			}
		}
		return or;
	}

	public List<PreferenceGroup> findGroups(String groupKey) {
		DetachedCriteria c = DetachedCriteria.forClass(PreferenceGroup.class);
		if (StringUtils.isNotBlank(groupKey)) {
			SimpleExpression keyC = Restrictions.like("key", groupKey.trim(),MatchMode.ANYWHERE).ignoreCase();
			SimpleExpression nameC = Restrictions.like("name", groupKey.trim(),MatchMode.ANYWHERE).ignoreCase();
			SimpleExpression descC = Restrictions.like("description", groupKey.trim(),MatchMode.ANYWHERE).ignoreCase();
			c.add(any(keyC,nameC,descC));
		}
		c.addOrder(Order.asc("applicationKey"));
		c.addOrder(Order.asc("name"));
		
		List<PreferenceGroup> objs = list(PreferenceGroup.class, c);
		return objs;
	}
	
	public List<Preference> findPreferences(String q) {
		DetachedCriteria c = DetachedCriteria.forClass(Preference.class);
		if (StringUtils.isNotBlank(q)) {
			SimpleExpression keyC = Restrictions.like("value", q.trim(),MatchMode.ANYWHERE).ignoreCase();
			SimpleExpression nameC = Restrictions.like("userId", q.trim(),MatchMode.ANYWHERE).ignoreCase();

			c.createAlias("specification", "s");
			SimpleExpression sNameC = Restrictions.like("s.name", q.trim(),MatchMode.ANYWHERE).ignoreCase();
			SimpleExpression sKeyC = Restrictions.like("s.key", q.trim(),MatchMode.ANYWHERE).ignoreCase();
			
			c.createAlias("specification.group", "g");
			SimpleExpression appKeyC = Restrictions.like("g.applicationKey", q.trim(),MatchMode.ANYWHERE).ignoreCase();
			
			c.add(any(keyC,nameC,sNameC,sKeyC,appKeyC));

		}
		
		c.addOrder(Order.asc("userId"));
		
		List<Preference> objs = list(c);
		return objs;
	}

	public List<PreferenceSpec> findSpecs(String q) {
		DetachedCriteria c = DetachedCriteria.forClass(PreferenceSpec.class);
		if (StringUtils.isNotBlank(q)) {
			SimpleExpression keyC = Restrictions.like("key", q.trim(),MatchMode.ANYWHERE).ignoreCase();
			SimpleExpression nameC = Restrictions.like("name", q.trim(),MatchMode.ANYWHERE).ignoreCase();
			SimpleExpression descC = Restrictions.like("description", q.trim(),MatchMode.ANYWHERE).ignoreCase();
			c.add(any(keyC,nameC,descC));
		}
		
		List<PreferenceSpec> objs = list(PreferenceSpec.class, c);
		return objs;
	}

	
	public Preference savePreference(String userId, PreferenceSpec spec,
			String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public PreferenceGroup getGroup(Long groupId) {
		PreferenceGroup pg = null;
		if (groupId != null) {
			try {
			pg = (PreferenceGroup) getHibernateTemplate().load(PreferenceGroup.class, groupId);
			} catch (ObjectRetrievalFailureException orfe) {
				// do nothing
				orfe.printStackTrace();
			}
		}
		return pg;
	}
	public Preference getPreference(Long prefId) {
		Preference pg = null;
		if (prefId != null) {
			try {
			pg = (Preference) getHibernateTemplate().load(Preference.class, prefId);
			} catch (ObjectRetrievalFailureException orfe) {
				// do nothing
				orfe.printStackTrace();
			}
		}
		return pg;
	}

	public PreferenceSpec getSpec(Long specId) {
		PreferenceSpec pg = null;
		if (specId != null) {
			try {
			pg = (PreferenceSpec) getHibernateTemplate().load(PreferenceSpec.class, specId);
			} catch (ObjectRetrievalFailureException orfe) {
				// do nothing
				orfe.printStackTrace();
			}
		}
		return pg;
	}

	public void deleteGroup(Long groupId) {
		PreferenceGroup group = getGroup(groupId);
		if (group != null) {
			getHibernateTemplate().delete(group);
		}
		
	}

	public void deleteSpec(Long specId) {
		PreferenceSpec spec = getSpec(specId);
		if (spec != null) {
			getHibernateTemplate().delete(spec);
		}
	}
	public void deletePreference(Long preferenceId) {
		Preference p = getPreference(preferenceId);
		if (p != null) {
			getHibernateTemplate().delete(p);
		}
	}
	


	public List<String> getApplicationKeys() {
		DetachedCriteria c = DetachedCriteria.forClass(PreferenceGroup.class);
		Projection distinct = Projections.distinct(Projections.property("applicationKey"));
		c.setProjection(distinct);
		c.addOrder(Order.asc("applicationKey"));
		return list(String.class, c);
	}
	
	
	@Override
	protected Class<? extends DomainObject> domainClass() {
		return Preference.class;
	}
}
