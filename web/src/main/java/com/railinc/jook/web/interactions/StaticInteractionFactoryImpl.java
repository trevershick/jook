package com.railinc.jook.web.interactions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.railinc.jook.JookManagementService;
import com.railinc.jook.domain.StaticInteraction;
import com.railinc.jook.interaction.JookInteraction;
import com.railinc.sso.rt.UserService;


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
		List<StaticInteraction> active = getService().active(UserService.getInstance().isAuthenticated(request));
		List<JookInteractionVO> al = new ArrayList<JookInteractionVO>(active.size());
		boolean authenticated = UserService.getInstance().isAuthenticated(request);
		for (StaticInteraction si : active) {
			if (authenticated && si.getSecureUrl() != null) {
				al.add(new JookInteractionVO(si.getType(), si.getTitle(), si.getSecureUrl(), false));
			} else if (!authenticated &&si.getUnsecureUrl() != null) {
				al.add(new JookInteractionVO(si.getType(), si.getTitle(), si.getSecureUrl(), false));
			}
		}
		return al;
	}

}
