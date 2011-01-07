package com.railinc.jook.web.interactions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.railinc.jook.interaction.JookInteraction;
import com.railinc.jook.service.DowntimeService;
import com.railinc.jook.service.ViewTracker;
import com.railinc.jook.service.ViewTrackingService;
import com.railinc.jook.web.Constants;

public class DowntimeInteractionFactoryImpl implements JookInteractionFactory {
	public static final String VIEWTRACKING_APPNAME = "downtime";
	DowntimeService downtimeService;
	ViewTrackingService viewTracking;
	
	
	public ViewTrackingService getViewTracking() {
		return viewTracking;
	}

	public void setViewTracking(ViewTrackingService viewTracking) {
		this.viewTracking = viewTracking;
	}

	public DowntimeService getDowntimeService() {
		return downtimeService;
	}

	public void setDowntimeService(DowntimeService downtimeService) {
		this.downtimeService = downtimeService;
	}

	@Override
	public List<? extends JookInteraction> interactions(HttpServletRequest request) {
		String parameter = request.getParameter(Constants.HTTP_PARAM_JOOK_APP);
		List<JookInteraction> j = new ArrayList<JookInteraction>();
		
		// if ur not logged in, then return
		if (request.getRemoteUser() == null) {
			return j;
		}
		String user = request.getRemoteUser();
		ViewTracker viewTracker = new ViewTracker(viewTracking, VIEWTRACKING_APPNAME, user);

		List<Long> keys = downtimeService.downtimeOverNextNDaysKeys(parameter, 30);
		
		if (!keys.isEmpty()) {
			// i want to shake the tab if the user has not seen all of the downtime.
			// i do still want to show the downtime though even if the user has seen them all.
			boolean shakeIt = viewTracker.userHasNotSeenAll(keys);
			j.add(new JookInteractionVO("tab","Downtime",request.getContextPath() +"/secured/services/downtime?content=tab&app=" + parameter,  shakeIt));
		}
		if (downtimeService.hasImminentDowntime(parameter)) {
			j.add(new JookInteractionVO("popup","Downtime",request.getContextPath() +"/secured/services/downtime?content=popup&app=" + parameter, false));
			
		}
		return j;
	}

}
