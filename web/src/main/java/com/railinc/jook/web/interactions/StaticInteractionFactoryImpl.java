package com.railinc.jook.web.interactions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.railinc.jook.JookManagementService;
import com.railinc.jook.domain.StaticInteraction;
import com.railinc.jook.interaction.JookInteraction;
import com.railinc.jook.web.Constants;

/**
 * returns the StaticJookInteraction objects from the database.
 * @author tshick
 *
 */
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
		
		String moduleId = request.getParameter(Constants.JOOK_PARAM_APP);
		boolean authenticated = request.getRemoteUser() != null;

		List<StaticInteraction> active = getService().active(authenticated);
		List<JookInteractionVO> al = new ArrayList<JookInteractionVO>(active.size());
		
		for (StaticInteraction si : active) {
			if (authenticated && si.getSecureUrl() != null && si.isAvailableForApp(moduleId)) {
				al.add(new JookInteractionVO(si.getType(), si.getTitle(), si.getSecureUrl(), false));
			} else if (!authenticated && si.getUnsecureUrl() != null && si.isAvailableForApp(moduleId)) {
				al.add(new JookInteractionVO(si.getType(), si.getTitle(), si.getSecureUrl(), false));
			}
		}
		return al;
	}

}
