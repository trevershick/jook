package com.railinc.jook.portlets;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSecurityException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
@RequestMapping("VIEW")
public class RapidCases {
	
	@RequestMapping  
    public String render(PortletRequest request, PortletResponse response, Model model) throws PortletSecurityException {
//		
//		PortletPreferences preferences = request.getPreferences();
//		String value = preferences.getValue("product", "Railsight");
//		Product productByName = service.getProductByName(value);
//		
////		long openTicketCount = rapidService.getOpenTicketCount(productByName);
//		
//		List<BasicRapidTicket> v = rapidService.getOpenProductTickets(productByName);
//		
//		
//		model.addAttribute("product", value);
//		model.addAttribute("tickets",v);
////		model.addAttribute("openTicketCount",openTicketCount);
		return "rapidcasecount";
	}


}
