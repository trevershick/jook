package com.railinc.jook.web.interactions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.railinc.jook.Jook;
import com.railinc.jook.domain.StaticInteraction;
import com.railinc.jook.interaction.JookInteraction;
import com.railinc.jook.service.DowntimeService;

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

		if (downtimeService.hasDowntimeOverNextNDays(parameter, 30)) {
			StaticInteraction i = new StaticInteraction();
			i.setTitle("Downtime");
			i.setType("tab");
			i.setUrl(request.getContextPath() +"/services/downtime?content=tab&app=" + parameter);
			j.add(i);
		}
		if (downtimeService.hasImminentDowntime(parameter)) {
			StaticInteraction i = new StaticInteraction();
			i.setType("popup");
			i.setUrl(request.getContextPath() +"/services/downtime?content=popup&app=" + parameter);
			j.add(i);
			
		}
		return j;
	}

}
