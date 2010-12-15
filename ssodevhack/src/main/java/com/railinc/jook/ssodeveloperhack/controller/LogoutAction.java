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


/**
 * @author Unascribed
 */
public class LogoutAction extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7932508928664648259L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		System.setProperty(LoginAction.SSO_USER_TAG,"");
		request.getSession().invalidate();
		
		resp.sendRedirect("login.do");
	}
	
	
}
