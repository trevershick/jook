package com.railinc.test.testjook.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author sdtxs01
 *
 */
public class SessionValidationFilter implements Filter {
	
	private SessionValidationInterceptor interceptor;
	private FilterConfig filterConfig;

	public void init(FilterConfig fc) throws ServletException {
		this.filterConfig = fc;
	}
	
	protected SessionValidationInterceptor interceptor() throws ServletException {
		if (this.interceptor == null) {
			WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
//			String[] beanDefinitionNames = context.getBeanDefinitionNames();
//			for (String b : beanDefinitionNames) {
//				System.err.println("Bean Def Name : "  + b);
//			}
			interceptor = (SessionValidationInterceptor) context.getBean("sessionValidationInterceptor");
		}
		if (interceptor == null) {
			throw new ServletException("Cannot get reference to 'sessionValidationInterceptor");
		}
		return interceptor;
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		try {
			if (interceptor().preHandle((HttpServletRequest) req, (HttpServletResponse) resp, null)) {
				chain.doFilter(req, resp);
			}
		} catch (ServletException se) {
			throw se;
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public void destroy() {
		
	}
}
