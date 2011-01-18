/**
 * Copyright (c) Railinc Corporation, 2009.
 * All rights reserved.
 */
package com.railinc.test.testjook.web;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import junit.framework.TestCase;

import com.railinc.sso.rt.LoggedUser;
import com.railinc.sso.rt.UserService;
/**
 * @author sdtxs01
 *
 */
public class SessionValidationInterceptorTest extends TestCase {


	private Cookie[] createCookies() {
		return new Cookie[]{
				 new Cookie(LoggedUser.SSO_USER_TAG + "1", "FIRST_NAME=Ganesh*LAST_NAME=Ramachandran*EMAIL=ganesh.\\ramachandran@railinc\\.com*"),
				 new Cookie(LoggedUser.SSO_USER_TAG + "2", "TITLE=Software Developer*COMPANY=RAIL - RAILINC CORPORATION*PHONE=1\\.919\\.6515308*FAX=1\\.919\\.6515000"),
				 new Cookie(LoggedUser.SSO_USER_TAG + "3", "*STATUS=ACTIVE*EMAILCONF=1*ADDRESS1=5001 Weston Parkway*ADDRESS2=Suite 200*CITY=Cary*STATE=NC*ZIP=27513*"),
				 new Cookie(LoggedUser.SSO_USER_TAG + "4", "COUNTRY=US*TYPE=undef*TYPE_DESC=Unknown*EMAIL_CHANGE_DATE=08-15-2005*ACCT_REVAL_DATE=08-01-2006*"),
				 new Cookie(LoggedUser.SSO_USER_TAG + "5", "USERID=rganesh*CUSTOM1=rganesh*CUSTOM2=*AUTHS=EHMSAPPADM&EHMSAPPUSR.BNSF|NS|CN|CSXT|UP&EHMSCARREPAIRHISTORY.BNSF"),
				 new Cookie(LoggedUser.SSO_USER_TAG + "6", "|UP|CSXT&EHMSDETECTCALUPLOAD&EHMSCOMPADM")
		};
	}
	

	/**
	 * Test method for {@link com.railinc.test.test105_7_a.web.SessionValidationInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)}.
	 * @throws Exception 
	 */
	public void testPreHandle_GA_cookies_are_set_logged_user_created() throws Exception {
		HttpSession session = createMock(HttpSession.class);
		HttpServletRequest request = createMock(HttpServletRequest.class);
		HttpServletResponse response = createMock(HttpServletResponse.class);
		
		
		expect(request.getHeader(UserService.USER_TAG)).andReturn("rganesh");
		expect(request.getSession()).andReturn(session);
		expect(session.getAttribute(UserService.SSO_USER_TAG)).andReturn(null);
		expect(request.getHeader(UserService.USER_TAG)).andReturn("rganesh");
		expect(request.getCookies()).andReturn(createCookies());
		expect(request.getSession()).andReturn(session);
		session.setAttribute(eq(UserService.SSO_USER_TAG), anyObject());
		
		replay(request);
		replay(session);
		replay(response);
		
		SessionValidationInterceptor i = new SessionValidationInterceptor();
		assertTrue(i.preHandle(request, response, null));
		
	}

	public void testPreHandle_GA_cookies_not_present() throws Exception {
		HttpSession session = createMock(HttpSession.class);
		HttpServletRequest request = createMock(HttpServletRequest.class);
		HttpServletResponse response = createMock(HttpServletResponse.class);
		String redirectUrl = "http://redirect.to.here";
		
		expect(request.getHeader(UserService.USER_TAG)).andReturn(null);
		expect(request.getSession()).andReturn(session);
		expect(session.getAttribute(UserService.SSO_USER_TAG)).andReturn(null);
		expect(request.getHeader(UserService.USER_TAG)).andReturn(null);
		expect(request.getCookies()).andReturn(null);
		expect(request.getSession()).andReturn(session);
		session.setAttribute(eq(UserService.SSO_USER_TAG), anyObject());
		response.sendRedirect(redirectUrl);
		
		
		replay(request);
		replay(session);
		replay(response);
		
		SessionValidationInterceptor i = new SessionValidationInterceptor();
		i.setLoginUrl(redirectUrl);
		assertFalse(i.preHandle(request, response, null));
		i.postHandle(request, response, null, null);
		i.afterCompletion(request, response, null, null);
		
	}

}
