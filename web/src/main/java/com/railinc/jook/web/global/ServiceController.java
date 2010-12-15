package com.railinc.jook.web.global;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.railinc.jook.domain.Preference;
import com.railinc.jook.service.UserPreferenceService;

@Controller
@RequestMapping("global/service/preferences")
public class ServiceController {
	UserPreferenceService service;
	
	
	public UserPreferenceService getService() {
		return service;
	}


	public void setService(UserPreferenceService service) {
		this.service = service;
	}


	/**
	 * obtains the application preferences for a user and returns the values in xml
	 * @param response
	 * @param app
	 * @param user
	 * @throws IOException
	 */
	@RequestMapping
	public void UserPreferencesJsonThisIsDefault(HttpServletResponse response, @RequestParam("app") String app, @RequestParam("user") String user) throws IOException {
		response.setContentType("application/json");
		/**
		 * yep, this should be done via json libraries probably
		 */
		List<Preference> userPreferences = getService().getUserPreferences(app, user);
		PrintWriter writer = response.getWriter();
		writer.write("{");

		
		Collection<String> groupKeys = groupKeys(userPreferences);
		boolean firstGroup = true;
		for (String groupKey : groupKeys) {
			List<Preference> preferencesByGroup = preferencesByGroup(userPreferences, groupKey);
			if (!firstGroup) {
				comma(writer);
			}
			writer.append("'");
			writer.append(groupKey);
			writer.append("'");
			writer.append(" : {");
			boolean firstPref = true;
			for (Preference p : preferencesByGroup) {
				if (!firstPref) {
					comma(writer);
				}
				nv(writer, p.getSpecification().getKey(), p.getValue());
				firstPref = false;
			}
			writer.append("}");
			
			
			
			firstGroup = false;
			
		}
		
		writer.write("}");
		writer.close();
	}
	
	/**
	 * obtains the application preferences for a user and returns the values in xml
	 * @param response
	 * @param app
	 * @param user
	 * @throws IOException
	 */
	@RequestMapping(params={"format=xml"})
	public void UserPreferencesXml(HttpServletResponse response, @RequestParam("app") String app, @RequestParam("user") String user) throws IOException {
		response.setContentType("text/xml");
		/**
		 * yeah, this should be jaxb or stax writer or something...
		 */
		List<Preference> userPreferences = getService().getUserPreferences(app, user);
		PrintWriter writer = response.getWriter();
		writer.append("<?xml version=\"1.0\"?>");
		writer.append("<preferences>");
		
		Collection<String> groupKeys = groupKeys(userPreferences);
		for (String groupKey : groupKeys) {
			List<Preference> preferencesByGroup = preferencesByGroup(userPreferences, groupKey);
			writer.append("<");
			writer.append(groupKey);
			writer.append(">");

			for (Preference p : preferencesByGroup) {
				String k =p.getSpecification().getKey();
				writer.append("<").append(k).append(">");
				writer.append(p.getValue());
				writer.append("</").append(k).append(">");
			}
			writer.append("</").append(groupKey).append(">");
			
			
			
		}
		
		writer.append("</preferences>");
		writer.close();
	}
	
	private List<Preference> preferencesByGroup(
			List<Preference> userPreferences, String groupKey) {
		List<Preference> results = new ArrayList<Preference>();
		for (Preference p : userPreferences) {
			if (StringUtils.equals(p.getSpecification().getGroup().getKey(), groupKey)) {
				results.add(p);
			}
		}
		return results;
	}

	/**
	 * returns a unique collection of group keys from the list of user preferences
	 * @param userPreferences
	 * @return
	 */
	private Collection<String> groupKeys(List<Preference> userPreferences) {
		TreeSet<String> treeSet = new TreeSet<String>();
		for (Preference p : userPreferences) {
			treeSet.add(p.getSpecification().getGroup().getKey());
		}
		return treeSet;
	}


	
	private final PrintWriter comma(PrintWriter w) {
		w.append(",");
		return w;
	}
	
	private final PrintWriter nv(PrintWriter w, String name, Object value) {
		w.append("'").append(name).append("'").append(" : ").append("'").append(String.valueOf(value)).append("'");
		return w;
	}
}
