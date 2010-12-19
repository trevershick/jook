package com.railinc.jook.web.client.v1_0;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DependenciesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1268570749296461246L;

//	private JookService jookService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		String moduleId = req.getParameter("app");
//		List<JookInteractionProvider> providersForModuleId = jookService.providersForModuleId(moduleId);
//		req.setAttribute("providers", providersForModuleId);
		resp.setContentType("text/javascript");
		resp.setHeader("Cache-Control", "max-age=0, private");
		req.getRequestDispatcher("/WEB-INF/client/1.0/core/jook_deps_js.jsp").include(req, resp);
	}

//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
//		jookService = (JookService) context.getBean("jookService");
//	}
//
//	
}
