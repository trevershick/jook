package com.railinc.jook.service.impl;

import java.util.List;

import com.railinc.jook.domain.DomainObject;
import com.railinc.jook.domain.Feedback;
import com.railinc.jook.service.FeedbackService;


public class FeedbackServiceImpl extends BaseServiceImpl<Feedback> implements FeedbackService {

	
	
	public FeedbackServiceImpl() {

	}
	
	
	@Override
	public long getCountOfFeedback() {
		return count();
	}

	@Override
	public List<Feedback> getFeedback(int start, int count, String sortColumn,
			boolean ascending) {
		return list(start,count,sortColumn,ascending);	}
	
	@Override
	public void createFeedback(String moduleId, String ssoUserId, String userName,
			String product, String type, String content) {

		Feedback feedback = new Feedback();
		feedback.setContent(content);
		feedback.setUserName(userName);
		feedback.setSsoModuleId(moduleId);
		feedback.setSsoUserId(ssoUserId);
		feedback.setType(type);
		saveOrUpdate(feedback);
	
	}
	
	public Feedback getFeedbackById(Long id) {
		return get(id);
	}
	@Override
	protected Class<? extends DomainObject> domainClass() {
		return Feedback.class;
	}
	
}
