package com.railinc.jook.web.client.v1_0;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.railinc.jook.Jook;
import com.railinc.jook.service.ConfigurationService;
import com.railinc.jook.web.Constants;

public abstract class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7979045855265537047L;
	private ConfigurationService configurationService;
	private static final Log log = LogFactory.getLog(BaseServlet.class);
	
	protected String setTheme(HttpServletRequest req) {
		String app = req.getParameter(Constants.HTTP_PARAM_JOOK_APP);
		String key = String.format(Constants.PROPKEY_APP_THEME_FORMAT, app);
		String theme = configurationService.getStringValue(key, Constants.REQUEST_ATTR_THEME_DEFAULT);
		if (log.isDebugEnabled()) {
			log.debug(String.format("Getting theme for app %s with key %s returned ",app,key,theme));
		}
		
		req.setAttribute(Constants.REQUEST_ATTR_THEME_KEY, theme);
		return theme;
	}
	
	protected int getIntProperty(String key, int defaultValue) {
		if (configurationService == null) { return defaultValue; }
		String stringValue = configurationService.getStringValue(key);
		if (null == stringValue) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(stringValue);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		configurationService = (ConfigurationService) context.getBean(Jook.SPRING_BEAN_NAME_CONFIG_SERVICE);
	}

	protected void setCacheControlHeader(HttpServletResponse resp, String propkey, int defaultMaxage) {
		resp.setHeader(Constants.HTTP_HEADER_CACHE_CONTROL, 
				String.format("max-age=%d, private", getIntProperty(propkey, defaultMaxage)));
	}

	
}
