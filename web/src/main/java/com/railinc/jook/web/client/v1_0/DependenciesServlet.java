package com.railinc.jook.web.client.v1_0;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.railinc.jook.web.Constants;

public class DependenciesServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1268570749296461246L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType(Constants.CONTENT_TYPE_JAVASCRIPT);
		
		setCacheControlHeader(resp, Constants.PROPKEY_CACHE_JOOKDEPS_MAXAGE, Constants.DEFAULT_CACHE_JOOKDEPS_MAXAGE);
		req.getRequestDispatcher("/WEB-INF/client/1.0/core/jook_deps_js.jsp").include(req, resp);
	}

}
