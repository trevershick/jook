package com.railinc.jook.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.railinc.jook.domain.DomainObject;
import com.railinc.jook.domain.Link;
import com.railinc.jook.service.LinkService;

public class LinkServiceImpl extends BaseServiceImpl<Link> implements LinkService {

	public void createLink(String path, String url, String description) {
		if (path == null || url == null || description == null) {
			throw new IllegalArgumentException("path,value and description may not be null.");
		}
		Link c = new Link();
		c.setPath(path);
		c.setUrl(url);
		c.setDescription(description);
	    getHibernateTemplate().save("Link", c);
	}

	@SuppressWarnings("unchecked")
	public Link getLink(String path) {
		List<Link> find = getHibernateTemplate().find("from Link WHERE path=?",path);
		if (find.size() == 0) {
			return null;
		}
		return (Link) find.get(0);
	}


	public List<Link> getLinks() {
		return list();
	}

	public void updateLink(String key, String value) {
		Link configurationValue = getLink(key);
		if (configurationValue != null) {
			Link i = (Link) configurationValue;
			if (StringUtils.isNotBlank(value)) {
				i.setUrl(value);
			}
			getHibernateTemplate().update(i);
		}
	}

	public void updateLink(String key, String value, String description) {
		Link configurationValue = getLink(key);
		if (configurationValue != null) {
			Link i = (Link) configurationValue;
			if (StringUtils.isNotBlank(value)) {
				i.setUrl(value);
			}
			if (StringUtils.isNotBlank(description)) {
				i.setDescription(description);
			}
			getHibernateTemplate().update(i);
		}
	}


	public void removeLink(String key) {
		Link configurationValue = getLink(key);
		if (configurationValue != null) {
			getHibernateTemplate().delete(configurationValue);
		}
		
	}

	@Override
	protected Class<? extends DomainObject> domainClass() {
		return Link.class;
	}
	
	
//	public ExampleObjects query(String text) {
//		LogManager.getLogger(getClass()).debug("query");
//		DetachedCriteria c = DetachedCriteria.forClass(ExampleObject.class);
//		c.add(Restrictions.like("text", text,MatchMode.ANYWHERE).ignoreCase());
//		
//		List<ExampleObject> objs = this.getHibernateTemplate().findByCriteria(c);
//		ExampleObjects exampleObjects = new ExampleObjects();
//		exampleObjects.addAll(objs);
//		return exampleObjects;
//		
//	}
//	
//	public ExampleObject findByKey(Long path) {
//		return (ExampleObject) getHibernateTemplate().get(ExampleObject.class	,path);
//	}
//
//	public ExampleObjects findByPath() {
//		LogManager.getLogger(getClass()).debug("find by path");
//		ExampleObjects exampleObjects = new ExampleObjects();
//		exampleObjects.addAll(this.getHibernateTemplate().loadAll(ExampleObject.class));
//		
//		if (exampleObjects.getExampleObjects().size() == 0) {
//			for (int i=0;i < 50;i++) {
//				ExampleObject exampleObject = new ExampleObject();
//				exampleObject.setText("Object # " + i);
//				getHibernateTemplate().save(exampleObject);
//			}
//			exampleObjects.addAll(this.getHibernateTemplate().loadAll(ExampleObject.class));
//		}
//		
//		
//		return exampleObjects;
//	}

}
