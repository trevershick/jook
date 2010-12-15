package com.railinc.jook.web.client.v1_0;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StyleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1268570749296461246L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		String moduleId = req.getParameter("app");
//		String ssoUser = req.getParameter("ssoUser");
		resp.setContentType("text/css");
		//resp.setHeader("Cache-Control", "max-age=300, private");

		if (null == req.getParameter("_k") || "true".equals(req.getParameter("_k"))) {
			req.getRequestDispatcher("/WEB-INF/client/1.0/core/jook_css.jsp").include(req,resp);
		}
		if (null == req.getParameter("_f") || "true".equals(req.getParameter("_f"))) {
			req.getRequestDispatcher("/WEB-INF/client/1.0/facebox/facebox_css.jsp").include(req,resp);
		}
	}

	

}
