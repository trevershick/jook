package com.railinc.jook.web.interactions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.railinc.jook.Collections;
import com.railinc.jook.JookManagementService;
import com.railinc.jook.Predicate;
import com.railinc.jook.domain.StaticInteraction;
import com.railinc.jook.interaction.JookInteraction;


public class StaticInteractionFactoryImpl implements JookInteractionFactory {
	JookManagementService service;
	
	public JookManagementService getService() {
		return service;
	}

	public void setService(JookManagementService v) {
		this.service = v;
	}

	@Override
	public List<? extends JookInteraction> interactions(HttpServletRequest request) {
//		String parameter = request.getParameter(Jook.JOOK_PARAM_APP);
		List<? extends JookInteraction> all = getService().active();
		return all;
	}

}
