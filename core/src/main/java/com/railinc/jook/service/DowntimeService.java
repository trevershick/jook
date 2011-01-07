package com.railinc.jook.service;

import java.util.Date;
import java.util.List;

import com.railinc.jook.domain.Downtime;

public interface DowntimeService {

	boolean hasImminentDowntime(String moduleId);
	Downtime imminentDowntime(String moduleId);
	List<Long> downtimeOverNextNDaysKeys(String moduleId, int nDays);
	List<Downtime> downtimeOverNextNDays(String moduleId, int nDays);
	
	long getCountOfDowntime();

	List<Downtime> getDowntime(int start, int count,
			String sortColumn, boolean ascending);

	void createDowntime(String moduleId, Date startTime, String content);

	Downtime getDowntimeById(Long id);

	void delete(Downtime f);
	void update(Downtime dt);

}