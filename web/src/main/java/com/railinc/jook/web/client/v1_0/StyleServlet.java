package com.railinc.jook.web.client.v1_0;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.railinc.jook.web.Constants;

public class StyleServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1268570749296461246L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		setCacheControlHeader(resp, Constants.PROPKEY_CACHE_JOOKSTYLE_MAXAGE, 
				Constants.DEFAULT_CACHE_JOOKSTYLE_MAXAGE);

		resp.setContentType("text/css");
		if (null == req.getParameter("_k") || "true".equals(req.getParameter("_k"))) {
			req.getRequestDispatcher("/WEB-INF/client/1.0/core/jook_css.jsp").include(req,resp);
		}
		if (null == req.getParameter("_f") || "true".equals(req.getParameter("_f"))) {
			req.getRequestDispatcher("/WEB-INF/client/1.0/facebox/facebox_css.jsp").include(req,resp);
		}
		req.getRequestDispatcher("/WEB-INF/client/1.0/jgrowl/jquery.jgrowl-1.2.5.css").include(req,resp);
	}

	

}
