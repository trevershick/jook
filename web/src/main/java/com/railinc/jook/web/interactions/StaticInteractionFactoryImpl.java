package com.railinc.jook.web.interactions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.railinc.jook.Collections;
import com.railinc.jook.JookManagementService;
import com.railinc.jook.Predicate;
import com.railinc.jook.domain.StaticInteraction;
import com.railinc.jook.domain.StaticInteraction.RoleInterrogator;
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
	public List<? extends JookInteraction> interactions(final HttpServletRequest request) {
		
		final String moduleId = request.getParameter(Constants.JOOK_PARAM_APP);

		final boolean secured = request.getRemoteUser() != null;

		List<StaticInteraction> active = getService().active(secured);
		List<JookInteractionVO> al = new ArrayList<JookInteractionVO>(active.size());

		

		// pick out the providers that have URLs that are secured or unsecured based on the secure variable
		active = Collections.selectAsList(active, new Predicate<StaticInteraction , Boolean>() {
			@Override
			public Boolean call(StaticInteraction o) {
				if (secured) {
					return o.getSecureUrl() != null;
				} else {
					return o.getUnsecureUrl() != null;
				}
			}
		});

		
		final RoleInterrogator interrogator = new RoleInterrogator() {
			@Override
			public boolean hasRole(String role) {
				return request.isUserInRole(role);
			}
		};

		active = Collections.selectAsList(active, new Predicate<StaticInteraction, Boolean>() {
			@Override
			public Boolean call(StaticInteraction o) {
				return o.isAvailableForApp(moduleId) && o.isAvailableToUser(interrogator);
			}
		});


		
				
		for (StaticInteraction si : active) {
			al.add(new JookInteractionVO(si.getType(), si.getTitle(), si.getSecureUrl(), false));
		}			
		return al;
	}

}
