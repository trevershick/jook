package com.railinc.jook.ssodeveloperhack.config;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class SSOBootstrapServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3656426143200944837L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		URL resource = getClass().getResource("/sso.properties");
		if (resource != null) {
			String externalForm = resource.toExternalForm();
			if (externalForm.startsWith("file:/")) {
				File f;
				try {
					f = new File(resource.toURI());
				} catch (URISyntaxException e) {
					f = new File( resource.getPath() );
				}
				System.setProperty("sso.configuration", f.getAbsolutePath());
			}
		}
	}

}
