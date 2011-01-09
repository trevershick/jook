/*
 * Created on May 2, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.railinc.jook.ssodeveloperhack.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.railinc.jook.ssodeveloperhack.config.Config;
import com.railinc.sso.rt.LoggedUser;
import com.railinc.sso.rt.UserService;

/**
 * @author rkainz
 */
public class LoginAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1339825910092765803L;

	// The next two properties should be kept in sync with properties defined in
	// the com.railinc.sso.rt.UserService and LoggedUser classes.
	static final String SSO_USER_TAG = UserService.SSO_USER_TAG;

	// Trever - Had to change this to SSO_USRDATA from SSO_USR_DATA because
	// ssort.jar wasn't picking
	// up the property that was set.
	private static final String LOGGED_USER_SSO_USER_TAG = LoggedUser.SSO_USER_TAG;

	private Config configuration;

	private UserService userService;
	
	
	public static String getLOGGED_USER_SSO_USER_TAG() {
		return LOGGED_USER_SSO_USER_TAG;
	}


	public Config getConfiguration() {
		if (this.configuration == null) {
			this.configuration = Config.getInstance();
		}
		return configuration;
	}




	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String userId = (String) request.getParameter("sso.userId");
		String authorizationString = null;
		
		if (userId == null) {
			userId = System.getProperty(SSO_USER_TAG);
		}
		if (userId != null) {
			authorizationString = getConfiguration().getProperty(userId);
		}
		
		
		if (authorizationString != null && userId != null) {
			System.setProperty(LOGGED_USER_SSO_USER_TAG, authorizationString);
			System.setProperty(SSO_USER_TAG, userId);
		} else {
			System.getProperties().remove(LOGGED_USER_SSO_USER_TAG);
			System.getProperties().remove(SSO_USER_TAG);
		}
		
		LoggedUser loggedUser = UserService.getLoggedUser(request);
		
		// the user is not authenticated, forward to the login template
		if (loggedUser == null || getUserService().isAuthenticated(request) == false) {
			debug("User is not Authenticated : %s", loggedUser);
			debug("Forwarding to mainAppLoginTemplate.jsp");
			getServletContext().getRequestDispatcher("/mainAppLoginTemplate.jsp").forward(request, resp);
			return;
		}
		
		debug("User %s is Logged In", loggedUser);
		resp.sendRedirect(buildRedirectUrl(request));
	}
	
	
	
	public String buildRedirectUrl(HttpServletRequest request) {
		String url = request.getParameter("app"); //getConfiguration().getProperty("appUrl");
		debug("Redirect URL : %s", url);
		String template = "%s://%s:%d%s";
		String protocol = (request.isSecure() ? "https" : "http");
		int port = request.getServerPort();
		String hostname = request.getServerName();
		String fullUrl = String.format(template, protocol, hostname, port, url);
		debug("Complete Redirect URL : %s", fullUrl);
		return fullUrl;
	}		

	
	public void debug(String out, Object ... objects) {
		if (isDebug()) {
			System.out.println(String.format(out, objects));
		}
	}
	/**
	 * @return
	 */
	private UserService getUserService() {
		if (this.userService == null) {
			this.userService = UserService.getInstance();
		}
		return this.userService;
	}


	public boolean isDebug() {
		return Boolean.valueOf(System.getProperty("com.railinc.sso.rt.debug"));
	}


}
