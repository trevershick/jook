package com.railinc.jook.portlets;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSecurityException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
@RequestMapping("VIEW")
public class BuildStatus {
		
	
	@RequestMapping  
    public String render(PortletRequest request, PortletResponse response, Model model) throws PortletSecurityException {
//		
//		PortletPreferences preferences = request.getPreferences();
//		String value = preferences.getValue("product", "Railsight");
//		Product productByName = service.getProductByName(value);
//		Set<BuildDefinition> bds = productByName.getBuildDefinitions();
//		List<Map<String,String>> ret = new ArrayList<Map<String,String>>();
//		for (BuildDefinition bd : bds) {
//			Map<String,String> bdm = new HashMap<String,String>();
//			bdm.put("name", bd.getName());
//			bdm.put("status", buildService.getLatestBuildStatus(bd).toString());
//			bdm.put("purpose", bd.getPurpose().toString());
//			ret.add(bdm);
//		}
//		
//		model.addAttribute("product", value);
//		model.addAttribute("status", ret);
		return "buildstatus";
	}



}
