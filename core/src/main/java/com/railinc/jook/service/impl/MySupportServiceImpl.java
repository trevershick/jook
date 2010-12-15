package com.railinc.jook.service.impl;

import java.util.List;

import com.railinc.jook.domain.DomainObject;
import com.railinc.jook.domain.Incident;
import com.railinc.jook.service.MySupportService;

public class MySupportServiceImpl extends BaseServiceImpl<Incident> 
	implements MySupportService {

	
	@Override
	protected Class<? extends DomainObject> domainClass() {
		return Incident.class;
	}

	@Override
	public void saveIncident(Incident incident) {
		saveOrUpdate(incident);
	}

	@Override
	public Incident getIncidentById(Long id) {
		return super.get(id);
	}

	@Override
	public void delete(Incident incident) {
		super.delete(incident);
	}

	@Override
	public List<Incident> getIncident(int start, int count, String sortBy,
			boolean ascending) {
		return super.list(start, count, sortBy, ascending);
	}

	@Override
	public Long getIncidentCount() {
		return count();
		
	}
}

