package com.railinc.jook.service;

import java.util.List;

import com.railinc.jook.domain.Feedback;

public interface FeedbackService {
	List<Feedback> getFeedback(int start, int count, String sortColumn, boolean ascending);

	long getCountOfFeedback();
	

	void createFeedback(String moduleId, String ssoUserId, String userName,
			String product, String type, String content);

	Feedback getFeedbackById(Long id);
}
