package com.railinc.jook.portlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VersionServlet
 */
public class VersionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String version = null;
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		InputStream resourceAsStream = VersionServlet.class.getResourceAsStream("/messages.properties");
		if (resourceAsStream == null) {
			throw new ServletException("Unable to locate messages.properties");
		}
		Properties properties = new Properties();
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			throw new ServletException("Unable to load messages.properties", e);
		}
		version = properties.getProperty("portlet.version");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fmt = request.getParameter("fmt");
		if (fmt == null) {
			fmt = "text/plain";
		}
		if ("html".equals(fmt)) {
			fmt = "text/html";
		}
		response.setContentType(fmt);
		PrintWriter writer = response.getWriter();
		writer.write(version);
		writer.close();
	}
}
