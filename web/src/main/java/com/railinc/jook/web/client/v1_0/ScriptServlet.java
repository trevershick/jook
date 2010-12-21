package com.railinc.jook.web.client.v1_0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.railinc.jook.Jook;
import com.railinc.jook.domain.JookInteractionProvider;
import com.railinc.jook.service.JookService;
import com.railinc.jook.web.Constants;
/**
 * this is what loops over the interaction providers adn return jook.js. This should
 * be as FAST as possible.
 * 
 * @author tshick
 *
 */
public class ScriptServlet extends BaseServlet {

	

	public static final String MODEL_PROVIDER_URL_COLLECTION = "providers";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1268570749296461246L;

	private JookService jookService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String moduleId = req.getParameter(Constants.JOOK_PARAM_APP);
		List<String> urls = new ArrayList<String>();
		
		List<JookInteractionProvider> providersForModuleId = jookService.providersForModuleId(moduleId);
		
		boolean secured = req.getRemoteUser() != null;
		for (JookInteractionProvider p : providersForModuleId) {
			if (secured && p.getSecureUrl() != null && p.isAvailableForApp(moduleId)) {
				urls.add(p.getSecureUrl());
			} else if (!secured && p.getUnsecureUrl() != null && p.isAvailableForApp(moduleId)) {
				urls.add(p.getUnsecureUrl());
			}
		}
		
		req.setAttribute(MODEL_PROVIDER_URL_COLLECTION, urls);
		resp.setContentType(Constants.CONTENT_TYPE_JAVASCRIPT);

		// 180 seconds
		setCacheControlHeader(resp, Constants.PROPKEY_CACHE_JOOKSCRIPT_MAXAGE, 
				Constants.DEFAULT_CACHE_JOOKSCRIPT_MAXAGE);

		req.getRequestDispatcher("/WEB-INF/client/1.0/core/jook_js.jsp").include(req, resp);

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		jookService = (JookService) context.getBean(Jook.SPRING_BEAN_NAME_JOOK_SERVICE);
	}

	
}
