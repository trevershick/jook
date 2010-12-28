package com.railinc.common.hibernate.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.railinc.common.hibernate.ThreadBasedActorProvider;
import com.railinc.sso.rt.LoggedUser;
import com.railinc.sso.rt.UserService;

/**
 * Servlet Filter implementation class SSOUserProviderFilter
 */
public class SSOUserProviderFilter implements Filter {
	ThreadBasedActorProvider provider = null;
	
    /**
     * Default constructor. 
     */
    public SSOUserProviderFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			LoggedUser loggedUser = UserService.getLoggedUser((HttpServletRequest) request);
			
			if (loggedUser != null) {
				provider.setActor(loggedUser.getUserId());
			} else {
				provider.setActor(null);
			}
		}
		try {
			chain.doFilter(request, response);
		} finally {
			provider.unsetActor();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		ServletContext ctx = fConfig.getServletContext();
		WebApplicationContext actx = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		this.provider = actx.getBean(ThreadBasedActorProvider.class);
	}

}
