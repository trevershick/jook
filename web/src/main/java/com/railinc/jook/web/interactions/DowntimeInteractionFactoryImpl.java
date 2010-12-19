package com.railinc.jook.web.interactions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.railinc.jook.Jook;
import com.railinc.jook.interaction.JookInteraction;
import com.railinc.jook.service.DowntimeService;
import com.railinc.sso.rt.UserService;

public class DowntimeInteractionFactoryImpl implements JookInteractionFactory {
	DowntimeService downtimeService;
	
	public DowntimeService getDowntimeService() {
		return downtimeService;
	}

	public void setDowntimeService(DowntimeService downtimeService) {
		this.downtimeService = downtimeService;
	}

	@Override
	public List<? extends JookInteraction> interactions(HttpServletRequest request) {
		String parameter = request.getParameter(Jook.JOOK_PARAM_APP);
		List<JookInteraction> j = new ArrayList<JookInteraction>();
		if (!UserService.getInstance().isAuthenticated(request)) {
			return j;
		}

		if (downtimeService.hasDowntimeOverNextNDays(parameter, 30)) {
			j.add(new JookInteractionVO("tab","Downtime",request.getContextPath() +"/secured/services/downtime?content=tab&app=" + parameter, false));
		}
		if (downtimeService.hasImminentDowntime(parameter)) {
			j.add(new JookInteractionVO("popup","Downtime",request.getContextPath() +"/secured/services/downtime?content=popup&app=" + parameter, false));
			
		}
		return j;
	}

}
