/**
 * Copyright (c) Railinc Corporation, 2009.
 * All rights reserved.
 */
package com.railinc.test.testjook.web;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;

import org.springframework.web.context.WebApplicationContext;
/**
 * @author sdtxs01
 *
 */
public class SessionValidationFilterTest extends TestCase {

	/**
	 * Test method for {@link com.railinc.test.test105_7_a.web.SessionValidationFilter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)}.
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void testDoFilter_interceptor_returns_false_not_valid() throws ServletException, IOException {
		FilterConfig config = createMock(FilterConfig.class);
		HttpServletRequest request = createMock(HttpServletRequest.class);
		HttpServletResponse response = createMock(HttpServletResponse.class);
		ServletContext context = createMock(ServletContext.class);
		FilterChain chain = createMock(FilterChain.class);
		WebApplicationContext webctx = createMock(WebApplicationContext.class);
		SessionValidationInterceptor i = new SessionValidationInterceptor() {

			/* (non-Javadoc)
			 * @see com.railinc.test.test105_7_a.web.SessionValidationInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
			 */
			@Override
			public boolean preHandle(HttpServletRequest request,
					HttpServletResponse response, Object obj) throws Exception {
				return false;
			}
			
		};
		
		
		expect(config.getServletContext()).andReturn(context);
		expect(context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE)).andReturn(webctx);
		expect(webctx.getBean("sessionValidationInterceptor")).andReturn(i);
		
		
		replay(config);replay(request);replay(response);replay(context);replay(webctx);replay(chain);
		
		
		
		SessionValidationFilter sessionValidationFilter = new SessionValidationFilter();
		sessionValidationFilter.init(config);
		sessionValidationFilter.doFilter(request, response, chain);
	}

	
	
	public void testDoFilter_interceptor_returns_true_all_Ok() throws ServletException, IOException {
		FilterConfig config = createMock(FilterConfig.class);
		HttpServletRequest request = createMock(HttpServletRequest.class);
		HttpServletResponse response = createMock(HttpServletResponse.class);
		ServletContext context = createMock(ServletContext.class);
		FilterChain chain = createMock(FilterChain.class);
		WebApplicationContext webctx = createMock(WebApplicationContext.class);
		SessionValidationInterceptor i = new SessionValidationInterceptor() {

			/* (non-Javadoc)
			 * @see com.railinc.test.test105_7_a.web.SessionValidationInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
			 */
			@Override
			public boolean preHandle(HttpServletRequest request,
					HttpServletResponse response, Object obj) throws Exception {
				return true;
			}
			
		};
		
		
		expect(config.getServletContext()).andReturn(context);
		expect(context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE)).andReturn(webctx);
		expect(webctx.getBean("sessionValidationInterceptor")).andReturn(i);
		chain.doFilter(request, response);
		
		
		replay(config);replay(request);replay(response);replay(context);replay(webctx);replay(chain);

		
		
		SessionValidationFilter sessionValidationFilter = new SessionValidationFilter();
		sessionValidationFilter.init(config);
		sessionValidationFilter.doFilter(request, response, chain);
		
	}

}
