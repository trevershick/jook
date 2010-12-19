package com.railinc.jook.web.client.v1_0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.railinc.jook.domain.JookInteractionProvider;
import com.railinc.jook.service.JookService;
import com.railinc.sso.rt.UserService;
/**
 * this is what loops over the interaction providers adn return jook.js. This should
 * be as FAST as possible.
 * 
 * @author tshick
 *
 */
public class ScriptServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1268570749296461246L;

	private JookService jookService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String moduleId = req.getParameter("app");
		List<String> urls = new ArrayList<String>();
		
		List<JookInteractionProvider> providersForModuleId = jookService.providersForModuleId(moduleId);
		
		boolean secured = UserService.getInstance().isAuthenticated(req);
		for (JookInteractionProvider p : providersForModuleId) {
			if (secured && p.getSecureUrl() != null) {
				urls.add(p.getSecureUrl());
			} else if (!secured && p.getUnsecureUrl() != null) {
				urls.add(p.getUnsecureUrl());
			}
		}
		
		req.setAttribute("providers", urls);
		resp.setContentType("text/javascript");

		// 180 seconds
		resp.setHeader("Cache-Control", "max-age=1800, private");

		req.getRequestDispatcher("/WEB-INF/client/1.0/core/jook_js.jsp").include(req, resp);

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		jookService = (JookService) context.getBean("jookService");
	}

	
}
