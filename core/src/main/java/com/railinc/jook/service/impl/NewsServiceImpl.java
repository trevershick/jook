package com.railinc.jook.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.railinc.jook.domain.DomainObject;
import com.railinc.jook.domain.NewsItem;
import com.railinc.jook.service.NewsService;

public class NewsServiceImpl extends BaseServiceImpl<NewsItem> implements
		NewsService {

	@Override
	public List<NewsItem> showNews(String moduleId) {
		Date now = new Date();
		DetachedCriteria c = DetachedCriteria.forClass(this.domainClass());
		c.add(Restrictions.eq("published", Boolean.TRUE));
		c.add(Restrictions.le("launchDate", now));
		c.add(Restrictions.or(Restrictions.ge("expirationDate", now), Restrictions.isNull("expirationDate")));
		c.add(Restrictions.or(Restrictions.eq("moduleId", moduleId), Restrictions.isNull("moduleId")));
		c.addOrder(Order.desc("authoredDate"));
		return list(c, 0, 5);
	}

	@Override
	public List<NewsItem> getNewsItems(int start, int count, String sortBy,
			boolean ascending) {
		DetachedCriteria c = DetachedCriteria.forClass(this.domainClass());
		if (sortBy != null) {
			if (ascending) {
				c.addOrder(Order.asc(sortBy));
			} else {
				c.addOrder(Order.desc(sortBy));
			}
		}
		return list(c,start,count);
	}

	@Override
	public NewsItem save(NewsItem value) {
		this.saveOrUpdate(value);
		return value;
	}

	@Override
	public NewsItem getNewsItem(Long newsItemId) {
		if (newsItemId == null) {
			return null;
		}
		return get(newsItemId);
	}

	@Override
	public void delete(NewsItem newsItem) {
		super.delete(newsItem);

	}

	@Override
	protected Class<? extends DomainObject> domainClass() {
		return NewsItem.class;
	}

	@Override
	public List<Object> newsItemsToShow(String moduleId) {
		Date now = new Date();
		DetachedCriteria c = DetachedCriteria.forClass(this.domainClass());
		c.add(Restrictions.eq("published", Boolean.TRUE));
		c.add(Restrictions.le("launchDate", now));
		c.add(Restrictions.or(Restrictions.ge("expirationDate", now), Restrictions.isNull("expirationDate")));
		c.add(Restrictions.or(Restrictions.eq("moduleId", moduleId), Restrictions.isNull("moduleId")));
		c.setProjection(Projections.id());
		c.addOrder(Order.desc("authoredDate"));
		return list(Long.class, c);
	}

}
