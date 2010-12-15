package com.railinc.jook.web.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.railinc.jook.domain.ConfigurationValue;
import com.railinc.jook.service.ConfigurationService;


public class LinkPopulationInterceptor implements HandlerInterceptor {
	ConfigurationService service;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

	public ConfigurationService getService() {
		return service;
	}

	public void setService(ConfigurationService service) {
		this.service = service;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		List<ConfigurationValue> configurationValues = service.getConfigurationValues("link.");
		Map<String,String> links = new HashMap<String, String>();
		for (ConfigurationValue cv : configurationValues) {
			links.put(cv.getKey(), cv.getStringValue());
		}
		request.setAttribute("templateLinks", links);
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}
}
