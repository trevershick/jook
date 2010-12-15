package com.railinc.jook.web.secure;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.railinc.jook.domain.Incident;
import com.railinc.jook.domain.StaticInteraction;
import com.railinc.jook.service.MySupportService;
import com.railinc.jook.web.util.StandardController;

@Controller
@RequestMapping("secure/helpdesk")
public class HelpDeskController extends StandardController {

	
	@ModelAttribute("landingPageUrl")
	public String landingPageUrl(HttpServletRequest req) {
		return req.getContextPath() + "/mysupport/landing";
	}

	@ModelAttribute("totalCount")
	public Long totalCount() {
		return getService().getIncidentCount();
	}
	
	public MySupportService getService() {
		return service;
	}


	

	public void setService(MySupportService service) {
		this.service = service;
	}

	MySupportService service;
	

	@InitBinder
    public void initBinder(WebDataBinder dataBinder) {
//        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd hh:mm"), false));
    }

	@RequestMapping("")
	public String list() {
		return "redirect:helpdesk/list";
	}

	@RequestMapping("list")
	public String list(Model model) {
		List<Incident> findIncidents = getService().getIncident(0, Integer.MAX_VALUE, null, true);
		model.addAttribute("incidents", findIncidents);
		return ".view.incidents.list";
	}
	



	@RequestMapping("details")
	public String incidentUpdate(Incident value, ModelMap map) {
		Incident incidentValue = getService().getIncidentById(value.getId());
		map.addAttribute("incident", incidentValue);
		return ".view.incidents.details";
	}

	@RequestMapping(value="update",params="_eventId_delete")
	public String deleteIncident(@ModelAttribute("incident") Incident value, ModelMap map, HttpServletRequest r) {

		Incident p = getService().getIncidentById(value.getId());
		getService().delete(p);

		message(r, "The incident was deleted.");
		return "redirect:list";
	}


}
