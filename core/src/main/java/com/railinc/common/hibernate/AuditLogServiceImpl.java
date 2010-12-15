package com.railinc.common.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AuditLogServiceImpl extends HibernateDaoSupport implements AuditLogService {
	
	
	@Override
	public List<AuditLogRecord> records(String entityName, String entityId,
			String txRef) {
		DetachedCriteria c = DetachedCriteria.forClass(AuditLogRecord.class);
		c.add(Restrictions.eq("entityName", entityName));
		c.add(Restrictions.eq("txRef", txRef));
		c.add(Restrictions.eq("entityId", entityId));
		return getHibernateTemplate().findByCriteria(c);
	}

	@Override
	public List<String> txReferences(String entityName, String entityId) {
		DetachedCriteria c = DetachedCriteria.forClass(AuditLogRecord.class);
		c.add(Restrictions.eq("entityName", entityName));
		c.add(Restrictions.eq("entityId", entityId));
		c.setProjection(Projections.distinct(Projections.groupProperty("txRef")));
		c.addOrder(Order.asc("updatedDate"));
		return getHibernateTemplate().findByCriteria(c);
	}

	@Override
	public List<String> entityIdsInvolvedInTx(String txRef, String entityType) {
		DetachedCriteria c = DetachedCriteria.forClass(AuditLogRecord.class);
		c.add(Restrictions.eq("entityName", entityType));
		c.add(Restrictions.eq("txRef", txRef));
		c.setProjection(Projections.distinct(Projections.groupProperty("entityId")));
		return getHibernateTemplate().findByCriteria(c);
	}

	@Override
	public List<String> entityTypesInvolvedInTx(String txRef) {
		DetachedCriteria c = DetachedCriteria.forClass(AuditLogRecord.class);
		c.add(Restrictions.eq("txRef", txRef));
		c.setProjection(Projections.distinct(Projections.groupProperty("entityName")));
		return getHibernateTemplate().findByCriteria(c);
	}

}
