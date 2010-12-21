package com.railinc.jook.web.client.v1_0;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourceServlet extends HttpServlet {
	private static final String MIMETYPE_JS = "text/javascript";
	private static final String MIMETYPE_CSS = "text/css";
	private static final String MIMETYPE_GIF = "image/gif";
	private static final String MIMETYPE_JPG = "image/jpg";
	private static final String MIMETYPE_PNG = "image/png";
	private static final String EXT_PNG = "png";
	private static final String EXT_JPG = "jpg";
	private static final String EXT_GIF = "gif";
	private static final String EXT_CSS = "css";
	private static final String EXT_JS = "js";
	/**
	 * 
	 */
	private static final long serialVersionUID = -3898112367979276049L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getPathInfo();
		String ext = null;
		int pIdx = path.lastIndexOf('.');
		if (pIdx > -1) {
			ext = path.substring(pIdx + 1);
		}
		applyMimeType(ext,resp);
		resp.setHeader("Cache-Control", "max-age=3600, private");

		req.getRequestDispatcher("/WEB-INF/client/1.0" + path).forward(req, resp);
	}
	

	protected void applyMimeType( String extension, HttpServletResponse response) {
		String contentType = null;
		if (EXT_JS.equals(extension)) {
			contentType = MIMETYPE_JS;
		} else if (EXT_CSS.equals(extension)) {
			contentType = MIMETYPE_CSS;
		} else if (EXT_GIF.equals(extension)) {
			contentType = MIMETYPE_GIF;
		} else if (EXT_JPG.equals(extension)) {
			contentType = MIMETYPE_JPG;
		} else if (EXT_PNG.equals(extension)) {
			contentType = MIMETYPE_PNG;
		} 
		if ( null != contentType) {
			response.setContentType(contentType);
		}
	}
}
