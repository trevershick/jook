package com.railinc.jook.portlets;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSecurityException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
@RequestMapping("VIEW")
public class Deployments {
	
	
	@RequestMapping  
    public String render(PortletRequest request, PortletResponse response, Model model) throws PortletSecurityException {
		
//		PortletPreferences preferences = request.getPreferences();
//		String value = preferences.getValue("product", "Railsight");
//		Product productByName = service.getProductByName(value);
//		Set<Deployment> bds = productByName.getDeployments();
//		List<Map<String,String>> ret = new ArrayList<Map<String,String>>();
//		for (Deployment bd : bds) {
//			
//			Map<String,String> bdm = new HashMap<String,String>();
//			bdm.put("env", bd.getEnv());
//			bdm.put("location", bd.getLocation());
//			bdm.put("versionUrl", bd.getVersionUrl());
//			if (bd.getVersionUrl() != null) {
//				bdm.put("version", buildService.getDeployedVersion(bd));
//			}
//			ret.add(bdm);
//		}
//		
//		model.addAttribute("product", value);
//		model.addAttribute("deployments", ret);
		return "deployments";
	}




}
