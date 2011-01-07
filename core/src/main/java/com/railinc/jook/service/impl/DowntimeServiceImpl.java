package com.railinc.jook.service.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.railinc.jook.domain.DomainObject;
import com.railinc.jook.domain.Downtime;
import com.railinc.jook.service.DowntimeService;

public class DowntimeServiceImpl extends BaseServiceImpl<Downtime> implements DowntimeService {
	
	
	public DowntimeServiceImpl() {
	}

	
	
	/* (non-Javadoc)
	 * @see com.railinc.jook.downtime.DowntimeService#createDowntime(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createDowntime(String moduleId, Date startTime, String content) {
		Downtime dt = new Downtime();
		dt.setHtmlContent(content);
		dt.setModuleId(moduleId);
		dt.setStartTime(startTime);
		saveOrUpdate(dt);
	}
	
	@Override
	public void delete(Downtime f) {
		getHibernateTemplate().delete(f);
		
	}
	
	/* (non-Javadoc)
	 * @see com.railinc.jook.downtime.DowntimeService#getCountOfDowntime()
	 */
	@Override
	public long getCountOfDowntime() {
		DetachedCriteria c = DetachedCriteria.forClass(Downtime.class);
		c.setProjection(Projections.rowCount());
		return ((Number)getHibernateTemplate().findByCriteria(c).iterator().next()).longValue();
	}

	/* (non-Javadoc)
	 * @see com.railinc.jook.downtime.DowntimeService#getDowntime(int, int, java.lang.String, boolean)
	 */
	@Override
	public List<Downtime> getDowntime(int start, int count, String sortColumn,
			boolean ascending) {
		
		DetachedCriteria c = DetachedCriteria.forClass(Downtime.class);
		if (sortColumn != null){ 
			c.addOrder(Order.asc(sortColumn));
		}
		
		
		return list(c, start, count);
	}
	
	/* (non-Javadoc)
	 * @see com.railinc.jook.downtime.DowntimeService#getDowntimeById(java.lang.Long)
	 */
	public Downtime getDowntimeById(final Long id) {
			return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Downtime>() {
				@Override
				public Downtime doInHibernate(Session session)
						throws HibernateException, SQLException {
					Downtime load2 = (Downtime) session.load(Downtime.class, id);
					Hibernate.initialize(load2);
					return load2;
				}
			});
	}
	
	@Override
	public List<Downtime> downtimeOverNextNDays(String moduleId, int nDays) {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, nDays);
		Date until = c.getTime();
		
		DetachedCriteria crit = DetachedCriteria.forClass(domainClass());
		crit.add(Restrictions.ge("startTime", now));
		crit.add(Restrictions.le("startTime", until));
		
		return list(crit);
	}

	@Override
	public List<Long> downtimeOverNextNDaysKeys(String moduleId, int nDays) {
		
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, nDays);
		Date until = c.getTime();
		
		DetachedCriteria crit = DetachedCriteria.forClass(domainClass());
		crit.setProjection(Projections.id());
		crit.add(Restrictions.eq("moduleId", moduleId));
		crit.add(Restrictions.ge("startTime", now));
		crit.add(Restrictions.le("startTime", until));
		return list(Long.class, crit);
	}

	@Override
	public boolean hasImminentDowntime(String moduleId) {
		Date now = new Date();

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 30);
		Date until = c.getTime();
		
		DetachedCriteria crit = DetachedCriteria.forClass(domainClass());
		crit.add(Restrictions.eq("moduleId", moduleId));
		crit.add(Restrictions.ge("startTime", now));
		crit.add(Restrictions.le("startTime", until));
		return count(crit) > 0;
	}

	@Override
	public Downtime imminentDowntime(String moduleId) {
		Date now = new Date();

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 30);
		Date until = c.getTime();
		
		DetachedCriteria crit = DetachedCriteria.forClass(domainClass());
		crit.add(Restrictions.eq("moduleId", moduleId));
		crit.add(Restrictions.ge("startTime", now));
		crit.add(Restrictions.le("startTime", until));
		
		return single(crit);
	}



	@Override
	protected Class<? extends DomainObject> domainClass() {
		return Downtime.class;
	}



	@Override
	public void update(Downtime dt) {
		saveOrUpdate(dt);
		
	}
}
