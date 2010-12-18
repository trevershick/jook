package com.railinc.jook.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.railinc.jook.domain.DomainObject;

public abstract class BaseServiceImpl<T> extends HibernateDaoSupport {

	protected abstract Class<? extends DomainObject> domainClass();

	protected T single(List<T> results) {
		return results == null || results.size() == 0 ? null : (T) results.get(0);
	}

	protected T single(DetachedCriteria criteria) {
		List<T> results = list(criteria);
		return results == null || results.size() == 0 ? null : (T) results.get(0);
	}

	
	protected long count() {
		DetachedCriteria c = createCriteria();
		c.setProjection(Projections.rowCount());
		return ((Number) getHibernateTemplate().findByCriteria(c).iterator()
				.next()).longValue();
	}

	protected DetachedCriteria createCriteria() {
		return DetachedCriteria.forClass(domainClass());
	}
	
	protected long count(DetachedCriteria c) {
		c.setProjection(Projections.rowCount());
		return ((Number) getHibernateTemplate().findByCriteria(c).iterator()
				.next()).longValue();
	}

	@SuppressWarnings("unchecked")
	public List<T> list(int start, int count, String sortColumn,
			boolean ascending) {
		DetachedCriteria c = createCriteria();
		if (sortColumn != null) {
			c.addOrder(Order.asc(sortColumn));
		}

		return getHibernateTemplate().findByCriteria(c, start, count);
	}

	public void delete(DomainObject p) {
		getHibernateTemplate().delete(p);
	}

	@SuppressWarnings("unchecked")
	public T get(Long id) {
		return (T) getHibernateTemplate().load(domainClass(), id);
	}

	public <X> X get(Class<X> c, Long id) {
		return (X) getHibernateTemplate().load(c, id);
	}
	
	public void saveOrUpdate(DomainObject p) {
		getHibernateTemplate().saveOrUpdate(p);
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> list() {
		return getHibernateTemplate().findByCriteria(createCriteria());
	}

	@SuppressWarnings("unchecked")
	protected <Y> List<Y> list(Class<?> Y, DetachedCriteria crit) {
		return getHibernateTemplate().findByCriteria(crit);
	}

	protected List<T> list(DetachedCriteria crit) {
		return list(domainClass(), crit);
	}
	@SuppressWarnings("unchecked")
	protected List<T> list(DetachedCriteria crit, int start, int max) {
		return getHibernateTemplate().findByCriteria(crit,start, max);
	}
	@SuppressWarnings("unchecked")
	protected <X> List<X> list(Class<X> clazz, DetachedCriteria crit, int start, int max) {
		return getHibernateTemplate().findByCriteria(crit,start, max);
	}
}
