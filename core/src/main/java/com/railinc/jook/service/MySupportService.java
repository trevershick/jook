package com.railinc.jook.service;

import java.util.List;

import com.railinc.jook.domain.Incident;

public interface MySupportService {
	void saveIncident(Incident incident);

	Incident getIncidentById(Long id);

	void delete(Incident incident);

	List<Incident> getIncident(int start, int count, String sortBy, boolean ascending);

	Long getIncidentCount();
}
