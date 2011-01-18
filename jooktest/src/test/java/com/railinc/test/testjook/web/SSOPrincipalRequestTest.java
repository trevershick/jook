/**
 * Copyright (c) Railinc Corporation, 2009.
 * All rights reserved.
 */
package com.railinc.test.testjook.web;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import junit.framework.TestCase;

import com.railinc.sso.rt.LoggedUser;
import com.railinc.sso.rt.UserService;
/**
 * @author sdtxs01
 *
 */
public class SSOPrincipalRequestTest extends TestCase {
	private static final String SSO_EHMS_ADMIN = "FIRST_NAME=Ganesh*LAST_NAME=Ramachandran*EMAIL=ganesh.\\ramachandran@railinc\\.com*TITLE=Software Developer*COMPANY=RAIL - RAILINC CORPORATION*PHONE=1\\.919\\.6515308*FAX=1\\.919\\.6515000*STATUS=ACTIVE*EMAILCONF=1*ADDRESS1=5001 Weston Parkway*ADDRESS2=Suite 200*CITY=Cary*STATE=NC*ZIP=27513*COUNTRY=US*TYPE=undef*TYPE_DESC=Unknown*EMAIL_CHANGE_DATE=08-15-2005*ACCT_REVAL_DATE=08-01-2006*USERID=rganesh*CUSTOM1=rganesh*CUSTOM2=*AUTHS=EHMSAPPADM&EHMSAPPUSR.BNSF|NS|CN|CSXT|UP&EHMSCARREPAIRHISTORY.BNSF|UP|CSXT&EHMSDETECTCALUPLOAD&EHMSCOMPADM";

	/**
	 * Test method for {@link com.railinc.test.test105_08a.web.SSOPrincipalRequest#getRemoteUser()}.
	 */
	public void testGetRemoteUser() {
		LoggedUser lu = new LoggedUser("rganesh");
		lu.populateFromString(SSO_EHMS_ADMIN);
		HttpServletRequest request = createMock(HttpServletRequest.class);
		HttpSession session = createMock(HttpSession.class);
		
		expect(request.getHeader(UserService.USER_TAG)).andReturn("rganesh");
		expect(request.getHeader(UserService.USER_TAG)).andReturn("rganesh");
		expect(request.getSession()).andReturn(session);
		expect(session.getAttribute(UserService.SSO_USER_TAG)).andReturn(lu);
		expect(session.getAttributeNames()).andReturn(new Vector<String>().elements());
		expect(request.getSession()).andReturn(session);

		
		replay(request);
		replay(session);
		
		SSOPrincipalRequest req = new SSOPrincipalRequest(request);
		assertEquals("rganesh", req.getRemoteUser());
	}

	/**
	 * Test method for {@link com.railinc.test.test105_08a.web.SSOPrincipalRequest#getUserPrincipal()}.
	 */
	public void testGetUserPrincipal() {
		LoggedUser lu = new LoggedUser("rganesh");
		lu.populateFromString(SSO_EHMS_ADMIN);
		HttpServletRequest request = createMock(HttpServletRequest.class);
		HttpSession session = createMock(HttpSession.class);
		
		expect(request.getHeader(UserService.USER_TAG)).andReturn("rganesh");
		expect(request.getHeader(UserService.USER_TAG)).andReturn("rganesh");
		expect(request.getSession()).andReturn(session);
		expect(session.getAttribute(UserService.SSO_USER_TAG)).andReturn(lu);
		expect(session.getAttributeNames()).andReturn(new Vector<String>().elements());
		expect(request.getSession()).andReturn(session);

		
		replay(request);
		replay(session);
		
		SSOPrincipalRequest req = new SSOPrincipalRequest(request);
		assertEquals("rganesh", req.getUserPrincipal().getName());		
	}

	/**
	 * Test method for {@link com.railinc.test.test105_08a.web.SSOPrincipalRequest#isUserInRole(java.lang.String)}.
	 */
	public void testIsUserInRoleString() {
		LoggedUser lu = new LoggedUser("rganesh");
		lu.populateFromString(SSO_EHMS_ADMIN);
		HttpServletRequest request = createMock(HttpServletRequest.class);
		HttpSession session = createMock(HttpSession.class);
		
		expect(request.getHeader(UserService.USER_TAG)).andReturn("rganesh");
		expect(request.getHeader(UserService.USER_TAG)).andReturn("rganesh");
		expect(request.getSession()).andReturn(session);
		expect(session.getAttribute(UserService.SSO_USER_TAG)).andReturn(lu);
		expect(session.getAttributeNames()).andReturn(new Vector<String>().elements());
		expect(request.getSession()).andReturn(session);

		
		replay(request);
		replay(session);
		
		SSOPrincipalRequest req = new SSOPrincipalRequest(request);
		assertFalse(req.isUserInRole("fahrfegnugen"));
		assertTrue(req.isUserInRole("EHMSCARREPAIRHISTORY"));
		assertTrue(req.isUserInRole("BNSF\\EHMSCARREPAIRHISTORY"));
	}

}
