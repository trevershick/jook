package com.railinc.jook.web.client.v1_0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.railinc.jook.Collections;
import com.railinc.jook.Predicate;
import com.railinc.jook.interaction.JookInteraction;
import com.railinc.jook.web.Constants;
import com.railinc.jook.web.interactions.JookInteractionFactory;

public class InteractionsServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1268570749296461246L;

	private Collection<JookInteractionFactory> jookInteractionFactories;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Collection<JookInteraction> allInteractions = new ArrayList<JookInteraction>();
		boolean shakeSet = false;
		
		for (JookInteractionFactory f : jookInteractionFactories) {
			List<? extends JookInteraction> interactions = f.interactions(req);
			
			if (!shakeSet) {
				shakeSet = shakeSet(interactions);
			}
 			allInteractions.addAll(interactions);
		}
		Map<String, Collection<JookInteraction>> partition = Collections.partition(allInteractions, 
				new Predicate<JookInteraction,String>() {public String call(JookInteraction o) {return ((JookInteraction)o).getType();}});
		
		req.setAttribute("interactions", partition);
		// 3 minute max age
		

		// don't cache if 'shake' was set...
		if (shakeSet) {
			setCacheControlHeader(resp, Constants.PROPKEY_CACHE_INTERACTIONS_MAXAGE_SHAKE, 
					Constants.DEFAULT_CACHE_INTERACTIONS_MAXAGE_SHAKE);
		} else {
			setCacheControlHeader(resp, Constants.PROPKEY_CACHE_INTERACTIONS_MAXAGE_NOSHAKE, 
					Constants.DEFAULT_CACHE_INTERACTIONS_MAXAGE_NOSHAKE);
		}

		req.getRequestDispatcher("/WEB-INF/jsp/interactions/interactions.jsp").forward(req, resp);
	}

	private boolean shakeSet(List<? extends JookInteraction> jis) {
		for (JookInteraction ji : jis) {
			if(ji.shake()) { return true; }
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		Map<?,?> beansOfType = context.getBeansOfType(JookInteractionFactory.class);
		jookInteractionFactories = (Collection<JookInteractionFactory>) beansOfType.values();
	}

	
}
