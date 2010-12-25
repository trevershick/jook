package com.railinc.jook.web.interactions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.railinc.jook.interaction.JookInteraction;
import com.railinc.jook.service.NewsService;
import com.railinc.jook.service.ViewTrackingService;
import com.railinc.jook.web.Constants;

public class NewsInteractionFactoryImpl implements JookInteractionFactory {
	NewsService service;
	ViewTrackingService viewTracking;
	public static final String VIEWTRACKING_APPNAME = "news";
	public static final String VIEWTRACKING_RESOURCE = "/";
	
	public ViewTrackingService getViewTracking() {
		return viewTracking;
	}

	public void setViewTracking(ViewTrackingService viewTracking) {
		this.viewTracking = viewTracking;
	}

	public NewsService getNewsService() {
		return service;
	}

	public void setNewsService(NewsService downtimeService) {
		this.service = downtimeService;
	}

	@Override
	public List<? extends JookInteraction> interactions(HttpServletRequest request) {
		String parameter = request.getParameter(Constants.HTTP_PARAM_JOOK_APP);
		List<JookInteraction> j = new ArrayList<JookInteraction>();
		if (request.getRemoteUser() == null) {
			return j;
		}

		String user = request.getRemoteUser();
		List<?> newsItemIds = service.newsItemsToShow(parameter);
		if (!newsItemIds.isEmpty() && viewTracking.userHasNotSeenAll(user, VIEWTRACKING_APPNAME, newsItemIds)) {
			j.add(new JookInteractionVO("tab", "News", 
					request.getContextPath() +"/services/news?app=" + parameter,
					user != null));
		}
		
		return j;
	}

}
