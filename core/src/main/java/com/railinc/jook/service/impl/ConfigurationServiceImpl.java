package com.railinc.jook.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.railinc.jook.domain.ConfigurationValue;
import com.railinc.jook.domain.DomainObject;
import com.railinc.jook.service.ConfigurationService;

public class ConfigurationServiceImpl extends BaseServiceImpl<ConfigurationValue> implements ConfigurationService {

	public void createConfiguration(String key, String value, String description) {
		if (key == null || value == null || description == null) {
			throw new IllegalArgumentException("key,value and description may not be null.");
		}
		ConfigurationValue c = new ConfigurationValue();
		c.setKey(key);
		c.setStringValue(value);
		c.setDescription(description);
	    getHibernateTemplate().save("ConfigurationValue" , c);
	}

	public ConfigurationValue getConfigurationValue(String key) {
		DetachedCriteria c = DetachedCriteria.forClass(domainClass());
		c.add(Restrictions.eq("key", key));
		return single(c);
	}

	public List<ConfigurationValue> getConfigurationValues() {
		List<ConfigurationValue> l = list();
		if (l.size() == 0) {
			initialProperties();
			l = list();
		}
		return l;
	}

	
	public List<ConfigurationValue> getConfigurationValues(String startsWith) {
		DetachedCriteria c = DetachedCriteria.forClass(domainClass());
		c.add(Restrictions.like("key", startsWith.concat("%")));
		return list(c);
	}
	

	public void updateConfiguration(String key, String value) {
		ConfigurationValue configurationValue = getConfigurationValue(key);
		if (configurationValue != null) {
			ConfigurationValue i = (ConfigurationValue) configurationValue;
			if (StringUtils.isNotBlank(value)) {
				i.setStringValue(value);
			}
			getHibernateTemplate().update(i);
		}
	}

	public void updateConfiguration(String key, String value, String description) {
		ConfigurationValue configurationValue = (ConfigurationValue) getConfigurationValue(key);
		if (configurationValue != null) {
			ConfigurationValue i = (ConfigurationValue) configurationValue;
			if (StringUtils.isNotBlank(value)) {
				i.setStringValue(value);
			}
			if (StringUtils.isNotBlank(description)) {
				i.setDescription(description);
			}
			getHibernateTemplate().update(i);
		}
	}

	public String getStringValue(String key) {
		ConfigurationValue configurationValue = getConfigurationValue(key);
		String tmp = null;
		if (configurationValue != null) {
			tmp = configurationValue.getStringValue();
		}
		return tmp;
	}

	public void removeConfiguration(String key) {
		// Auto-generated method stub
		ConfigurationValue configurationValue = getConfigurationValue(key);
		if (configurationValue != null) {
			getHibernateTemplate().delete(configurationValue);
		}
		
	}

	public String getStringValue(String key, String defaultIfNull) {
		String tmp = getStringValue(key);
		if (tmp == null) {
			tmp = defaultIfNull;
		}
		return tmp;
	}
	
	
	private void initialProperties() {
	    createConfiguration("link.signout", "/sso/logout.do", "");
	    createConfiguration("link.launchpad","/rportal/web/csc","");
	    createConfiguration("link.userservices","/sso","");
	    createConfiguration("link.contactus", "/rportal/web/guest/contactus","");
   		createConfiguration("link.legalnotices","/rportal/web/guest/legal","");
		createConfiguration("link.privacy", "/rportal/web/guest/privacy", "");
		createConfiguration("link.termsofservice", "/rportal/web/guest/terms","");
		createConfiguration("sso.login.url", "/sso/logout.do","");
	}

	@Override
	protected Class<? extends DomainObject> domainClass() {
		return ConfigurationValue.class;
	}

}
