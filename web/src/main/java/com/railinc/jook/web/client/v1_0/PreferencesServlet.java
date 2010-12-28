package com.railinc.jook.web.client.v1_0;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.railinc.jook.Jook;
import com.railinc.jook.domain.Preference;
import com.railinc.jook.service.UserPreferenceService;


public class PreferencesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4024419349334286618L;
	private UserPreferenceService userPreferenceService;
	private Pattern pattern;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = req.getRemoteUser();
		String pathInfo = req.getPathInfo();
		
		if (user == null) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		Matcher matcher = pattern.matcher(pathInfo); 
		if (!matcher.matches()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, pathInfo);
			return;
		}
		// path , servlet_path/APPLICATION.(json|xml)
		String app = matcher.group(1);
		String format = matcher.group(2);
		
		// return the user's preferences
		List<Preference> userPreferences = userPreferenceService.getUserPreferences(app, user);
		
		if ("xml".equals(format)) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, pathInfo);
			return;
		} else {
			Writer writer = resp.getWriter();
			marshal(userPreferences, writer);
			writer.close();
		}
		
	}

	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = req.getRemoteUser();
		String pathInfo = req.getPathInfo();
		
		if (user == null) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		Matcher matcher = pattern.matcher(pathInfo); 
		if (!matcher.matches()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, pathInfo);
			return;
		}
		// path , servlet_path/APPLICATION.(json|xml)
		String app = matcher.group(1);
//		String format = matcher.group(2);
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		if (key == null || value == null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "key and value are required");
			return;
		}
		userPreferenceService.updateUserPreference(app, user, key, value);
		resp.setStatus(HttpServletResponse.SC_OK);
	}





	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		pattern = Pattern.compile("\\/([a-zA-Z0-9_\\-]+)\\.(json|xml)");
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		userPreferenceService = (UserPreferenceService) context.getBean(Jook.SPRING_BEAN_NAME_USERPREF_SERVICE);
	}

	@SuppressWarnings("unchecked")
	private void marshal(List<Preference> prefs, Writer writer) throws IOException {
		JSONObject o = new JSONObject();
		for (Preference p : prefs) {
			o.put(p.getSpecification().getKey(), toJsonObject(p));
		}
		o.writeJSONString(writer);
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject toJsonObject(Preference p) {
		JSONObject j = new JSONObject();
		j.put("key", p.getSpecification().getKey());
		j.put("value", p.getValue());
		j.put("defaultValue", p.getSpecification().getDefaultValue());
		j.put("name", p.getSpecification().getName());
		j.put("description", p.getSpecification().getDescription());
		return j;
	}
	
}
