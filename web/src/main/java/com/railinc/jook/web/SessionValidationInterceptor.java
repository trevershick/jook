/*
 * Copyright 2004, RAILINC. All rights reserved.
 */

package com.railinc.jook.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class SessionValidationInterceptor implements HandlerInterceptor {


	String loginUrl;
	
	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj)
			throws Exception {

		com.railinc.sso.rt.LoggedUser loggedUser = com.railinc.sso.rt.UserService
				.getLoggedUser(request);

		if (loggedUser == null || StringUtils.isEmpty(loggedUser.getUserId())) {
			response.sendRedirect(getLoginUrl());
			return false;
		}
		return true;
	}
	
	
	public void afterCompletion(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj,
			Exception exception) throws Exception {
		
	}

	public void postHandle(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj,
			ModelAndView modelandview) throws Exception {
		
	}

}
