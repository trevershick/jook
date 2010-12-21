package com.railinc.jook.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.railinc.jook.Collections;
import com.railinc.jook.JookManagementService;
import com.railinc.jook.Predicate;
import com.railinc.jook.domain.DomainObject;
import com.railinc.jook.domain.JookInteractionProvider;
import com.railinc.jook.domain.StaticInteraction;
import com.railinc.jook.service.JookService;

public class JookServiceImpl extends BaseServiceImpl<JookInteractionProvider> implements JookService, JookManagementService {


	List<JookInteractionProvider> providers = new ArrayList<JookInteractionProvider>();

	public  JookServiceImpl() {

	}

	@Override
	public List<JookInteractionProvider> providersForModuleId(String ssoModuleId) {
		return Collections.selectAsList(registeredProviders(0,Integer.MAX_VALUE,null,true), 
				new Predicate<JookInteractionProvider,Boolean>() {
					public Boolean call(JookInteractionProvider o) { return o.getActive();}	
				});
	}


	@Override
	public List<JookInteractionProvider> registeredProviders(int start,
			int count, String sortColumn, boolean ascending) {
		return this.list(start, count, sortColumn, ascending);
	}

	@Override
	public long registeredProviderCount() {
		return count();
	}

	@Override
	protected Class<? extends DomainObject> domainClass() {
		return JookInteractionProvider.class;
	}

	@Override
	public JookInteractionProvider getProvider(String nm) {
		DetachedCriteria c = DetachedCriteria.forClass(domainClass());
		c.add(Restrictions.eq("name", nm ));
		return single(c);
	}

	@Override
	public void updateProvider(JookInteractionProvider p) {
		// ate(p);
		saveOrUpdate(p);
	}

	@Override
	public void deleteProvider(JookInteractionProvider f) {
		delete(f);
	}

	@Override
	public void updateInteraction(StaticInteraction p) {
		saveOrUpdate(p);
	}

	@Override
	public void deleteInteraction(StaticInteraction p) {
		delete(p);
	}

	@Override
	public StaticInteraction getInteraction(Long id) {
		if (id == null) { return null; }
		return get(StaticInteraction.class,id);
	}

	@Override
	public List<StaticInteraction> interactions(int i, int maxValue,
			Object object, boolean b) {
		DetachedCriteria crit = DetachedCriteria.forClass(StaticInteraction.class);
		
		return this.list(StaticInteraction.class, crit, 0, Integer.MAX_VALUE);
	}
	@Override
	public List<StaticInteraction> active(boolean secured) {
		DetachedCriteria crit = DetachedCriteria.forClass(StaticInteraction.class).add(Restrictions.eq("active",Boolean.TRUE));
		crit.add(Restrictions.isNotNull(secured ? "secureUrl" : "unsecureUrl"));
		return this.list(StaticInteraction.class, crit);
	}
}
