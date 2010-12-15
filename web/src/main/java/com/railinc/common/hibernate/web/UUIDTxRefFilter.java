package com.railinc.common.hibernate.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.MDC;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.railinc.common.hibernate.UUIDTxRefProvider;

/**
 * Servlet Filter implementation class TxRefFilter
 */
public class UUIDTxRefFilter implements Filter {
	UUIDTxRefProvider provider = null;

	/**
	 * Default constructor.
	 */
	public UUIDTxRefFilter() {
		
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {
			String tx = provider.createTxRef();
			MDC.put("tx", tx);
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} finally {
			provider.unsetTxRef();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		ServletContext ctx = fConfig.getServletContext();
		WebApplicationContext actx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ctx);
		try {
			this.provider = actx.getBean(UUIDTxRefProvider.class);
		} catch (NoSuchBeanDefinitionException n) {
			throw new ServletException(n);
		}

	}
}
