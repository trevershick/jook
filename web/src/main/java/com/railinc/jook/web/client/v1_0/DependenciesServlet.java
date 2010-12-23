package com.railinc.jook.web.client.v1_0;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.railinc.jook.web.Constants;

public class DependenciesServlet extends BaseServlet {

	private static final String JOOK_DEPS_JSP = "/WEB-INF/client/1.0/core/jook_deps_js.jsp";
	private static final Log log = LogFactory.getLog(DependenciesServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1268570749296461246L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType(Constants.CONTENT_TYPE_JAVASCRIPT);
		setTheme(req);
		setCacheControlHeader(resp, Constants.PROPKEY_CACHE_JOOKDEPS_MAXAGE, Constants.DEFAULT_CACHE_JOOKDEPS_MAXAGE);
		if (log.isDebugEnabled()) {
			log.debug("Loading Dependencies JSP " + JOOK_DEPS_JSP);
		}
		req.getRequestDispatcher(JOOK_DEPS_JSP).include(req, resp);
	}

}
