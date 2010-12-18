package com.railinc.jook.service.impl;

import java.util.Date;

import org.hibernate.criterion.Restrictions;

import com.railinc.jook.domain.DomainObject;
import com.railinc.jook.domain.LastUserView;
import com.railinc.jook.service.ViewTrackingService;

public class ViewTrackingServiceImpl extends BaseServiceImpl<LastUserView> implements ViewTrackingService {

	@Override

	public void userJustSaw(String user, String application, String resource) {
		if (getHibernateTemplate().bulkUpdate("UPDATE LastUserView SET lastViewed=?", new Date()) == 0) {
			super.saveOrUpdate(new LastUserView(user,application,resource));
		}
	}
	@Override
	public boolean hasUserSeen(String user, String application, String resource) {
		return super.count(createCriteria().add(Restrictions.eq("name", resource)).add(Restrictions.eq("app", application))) > 0;
	}

	@Override
	public void resetViewState(String application, String resource) {
		getHibernateTemplate().bulkUpdate("DELETE FROM LastUserView WHERE app=? AND name=?", application,resource);
	}

	@Override
	protected Class<? extends DomainObject> domainClass() {
		return LastUserView.class;
	}

}
