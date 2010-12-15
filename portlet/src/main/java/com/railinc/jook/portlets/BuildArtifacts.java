package com.railinc.jook.portlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSecurityException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
@RequestMapping("VIEW")
public class BuildArtifacts {
	
	
	
	@RequestMapping  
    public String render(PortletRequest request, PortletResponse response, Model model) throws PortletSecurityException {
//		
//		PortletPreferences preferences = request.getPreferences();
//		String value = preferences.getValue("product", "Railsight");
//		Product productByName = service.getProductByName(value);
//		Set<BuildDefinition> bds = productByName.getBuildDefinitions();
//		List<Map<String,String>> ret = new ArrayList<Map<String,String>>();
//		for (BuildDefinition bd : bds) {
//			if (bd.getGroupId() == null || bd.getArtifactId() == null) {
//				continue;
//			}
//			Map<String,String> bdm = new HashMap<String,String>();
//			bdm.put("name", bd.getName());
//			bdm.put("groupId", bd.getGroupId());
//			bdm.put("artifactId", bd.getArtifactId());
//			bdm.put("latestRelease", buildService.getLatestReleaseArtifact(bd.getGroupId(), bd.getArtifactId()));
//			bdm.put("latestSnapshot", buildService.getLatestSnapshotArtifact(bd.getGroupId(), bd.getArtifactId()));
//			ret.add(bdm);
//		}
//		
//		model.addAttribute("product", value);
//		model.addAttribute("artifacts", ret);
		return "buildartifacts";
	}


}
