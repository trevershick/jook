package com.railinc.jook.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.railinc.jook.domain.Link;
import com.railinc.jook.service.LinkService;

/**
 * Servlet implementation class RedirectServlet
 */
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      LinkService service;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		String[] beanNamesForType = context.getBeanNamesForType(LinkService.class);
		if (null == beanNamesForType || 0 == beanNamesForType.length) {
			throw new ServletException("cannot find LinkService");
		}
		service = (LinkService) context.getBean(beanNamesForType[0]);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String removeThisFromPath = request.getContextPath().concat(request.getServletPath());
		String path = request.getRequestURI().substring(removeThisFromPath.length() + 1);
		Link link = service.getLink(path);
		String qs = request.getQueryString();
		if (null == link) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			response.sendRedirect(link.getUrl() + (null == qs ? "" : "?"+qs));
		}
	}

}
