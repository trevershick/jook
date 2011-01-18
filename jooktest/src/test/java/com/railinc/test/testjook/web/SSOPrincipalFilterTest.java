/**
 * Copyright (c) Railinc Corporation, 2009.
 * All rights reserved.
 */
package com.railinc.test.testjook.web;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;

/**
 * @author sdtxs01
 *
 */
public class SSOPrincipalFilterTest extends TestCase {

	static final class Holder<T> {
		T value = null;
	}

	/**
	 * Test method for {@link com.railinc.test105_02_a.web.SSOPrincipalFilter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)}.
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void testDoFilter_HttpRequest() throws IOException, ServletException {
		SSOPrincipalFilter filter = new SSOPrincipalFilter();
		
		final Holder<ServletRequest> holder = new Holder<ServletRequest>();
		
		FilterChain fc = new FilterChain() {
			public void doFilter(ServletRequest request,
					ServletResponse response) throws IOException,
					ServletException {
				holder.value = request;
			}
		};
		
		HttpServletRequest httpServletRequest = createMock(HttpServletRequest.class);
		HttpServletResponse httpServletResponse = createMock(HttpServletResponse.class);
		FilterChain filterChain = createMock(FilterChain.class);
		
		
		filterChain.doFilter((ServletRequest) anyObject(), (ServletResponse) anyObject());
		replay(httpServletRequest);
		replay(httpServletResponse);
		
		
		filter.init(null);
		filter.doFilter(httpServletRequest, httpServletResponse, fc);
		filter.destroy();
		verify(httpServletRequest);
		verify(httpServletResponse);
		
		
		assertTrue("The request object passed down the chain should be wrapped in an ssoprincipalrequest", holder.value instanceof SSOPrincipalRequest);
	}
	
	
	public void testDoFilter_NonHttpRequest() throws IOException, ServletException {
		SSOPrincipalFilter filter = new SSOPrincipalFilter();
		
		final Holder<ServletRequest> holder = new Holder<ServletRequest>();
		
		FilterChain fc = new FilterChain() {
			public void doFilter(ServletRequest request,
					ServletResponse response) throws IOException,
					ServletException {
				holder.value = request;
			}
		};
		
		ServletRequest servletRequest = createMock(ServletRequest.class);
		ServletResponse servletResponse = createMock(ServletResponse.class);
		FilterChain filterChain = createMock(FilterChain.class);
		
		
		
		filterChain.doFilter(servletRequest,servletResponse);
		replay(servletRequest);
		replay(servletResponse);
		
		
		filter.init(null);
		filter.doFilter(servletRequest, servletResponse, fc);
		filter.destroy();
		verify(servletRequest);
		verify(servletResponse);
		
		
		assertFalse("The request object passed down the chain should  NOT be wrapped in an ssoprincipalrequest", holder.value instanceof SSOPrincipalRequest);
	}
}
