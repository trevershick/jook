package com.railinc.jook.web.interactions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.railinc.jook.Jook;
import com.railinc.jook.domain.StaticInteraction;
import com.railinc.jook.interaction.JookInteraction;
import com.railinc.jook.service.NewsService;

public class NewsInteractionFactoryImpl implements JookInteractionFactory {
	NewsService service;
	
	public NewsService getNewsService() {
		return service;
	}

	public void setNewsService(NewsService downtimeService) {
		this.service = downtimeService;
	}

	@Override
	public List<? extends JookInteraction> interactions(HttpServletRequest request) {
		String parameter = request.getParameter(Jook.JOOK_PARAM_APP);
		List<JookInteraction> j = new ArrayList<JookInteraction>();

		if (service.hasNewsItemsToShow(parameter)) {
			StaticInteraction i = new StaticInteraction();
			i.setTitle("News");
			i.setType("tab");
			i.setUrl(request.getContextPath() +"/services/news?app=" + parameter);
			j.add(i);
		}
		
		return j;
	}

}
