package com.railinc.jook.web;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;

import com.railinc.sso.rt.LoggedUser;
import com.railinc.sso.rt.UserService;


public class SSOPrincipalRequest extends HttpServletRequestWrapper {
	private LoggedUser loggedUser;
	
	public SSOPrincipalRequest(HttpServletRequest original) {
		super(original);
	}

	public String getRemoteUser() {	
		LoggedUser loggedUser = loggedUser();
		if (loggedUser == null || StringUtils.isEmpty(loggedUser.getUserId())) {
			return null;
		}
		return loggedUser.getUserId();
	}

	private LoggedUser loggedUser() {
		if (this.loggedUser == null) {
			this.loggedUser = UserService.getLoggedUser((HttpServletRequest) getRequest());
		}
		return this.loggedUser;
	}


	public Principal getUserPrincipal() {
		LoggedUser loggedUser2 = loggedUser();
		if (loggedUser2 == null || StringUtils.isEmpty(loggedUser2.getUserId())) {
			return null;
		}
		final String userId = loggedUser2.getUserId(); 
		return new Principal() {
			public String getName() {
				return userId;
			}
		};
	}

	protected boolean userHasRole(String roleId, LoggedUser user) {
		if (user == null) {
			return false;
		}
		return user.hasRole(roleId);
	}
	
	protected boolean userHasRole(String roadMark, String roleId, LoggedUser user) {
		if (user == null) {
			return false;
		}
		Collection entities = user.getEntities(roleId);
		return entities != null && entities.contains(roleId);
	}

	public boolean isUserInRole(String arg0) {
		if (null == arg0) {
			return false;
		}
		if (arg0.contains("\\")) {
			String[] split = arg0.split("\\\\");
			String mark = split[0];
			String roleId = split[1];
			return userHasRole(roleId, loggedUser());
		} else {
			return userHasRole(arg0, loggedUser());
		}
	}

}