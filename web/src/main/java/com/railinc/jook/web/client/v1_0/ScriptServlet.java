package com.railinc.jook.web.client.v1_0;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.railinc.jook.Collections;
import com.railinc.jook.Jook;
import com.railinc.jook.Predicate;
import com.railinc.jook.domain.JookInteractionProvider;
import com.railinc.jook.domain.JookInteractionProvider.RoleInterrogator;
import com.railinc.jook.service.JookService;
import com.railinc.jook.web.Constants;

/**
 * this is what loops over the interaction providers adn return jook.js. This
 * should be as FAST as possible.
 * 
 * @author tshick
 * 
 */
public class ScriptServlet extends BaseServlet {

	public static final String MODEL_PROVIDER_URL_COLLECTION = "providers";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1268570749296461246L;

	private JookService jookService;

	@Override
	protected void doGet(final HttpServletRequest request,
			HttpServletResponse resp) throws ServletException, IOException {
		final String moduleId = request.getParameter(Constants.JOOK_PARAM_APP);

		List<JookInteractionProvider> providersForModuleId = jookService
				.providersForModuleId(moduleId);

		final boolean secured = request.getRemoteUser() != null;

		final RoleInterrogator interrogator = new RoleInterrogator() {
			@Override
			public boolean hasRole(String role) {
				return request.isUserInRole(role);
			}
		};

		providersForModuleId = Collections.selectAsList(providersForModuleId, new Predicate<JookInteractionProvider, Boolean>() {
			@Override
			public Boolean call(JookInteractionProvider o) {
				return o.isAvailableForApp(moduleId) && o.isAvailableToUser(interrogator);
			}
		});
		

		
		
		List<String> urls = Collections.map(providersForModuleId, new Predicate<JookInteractionProvider,String>() {
			@Override
			public String call(JookInteractionProvider o) {
				return secured ? o.getSecureUrl() : o.getUnsecureUrl();
			}
		});
			
		
		request.setAttribute(MODEL_PROVIDER_URL_COLLECTION, urls);
		resp.setContentType(Constants.CONTENT_TYPE_JAVASCRIPT);

		// 180 seconds
		setCacheControlHeader(resp, Constants.PROPKEY_CACHE_JOOKSCRIPT_MAXAGE,
				Constants.DEFAULT_CACHE_JOOKSCRIPT_MAXAGE);

		request.getRequestDispatcher("/WEB-INF/client/1.0/core/jook_js.jsp")
				.include(request, resp);

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		jookService = (JookService) context
				.getBean(Jook.SPRING_BEAN_NAME_JOOK_SERVICE);
	}

}
