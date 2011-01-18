package com.railinc.jook.web.secure;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.railinc.jook.domain.Downtime;
import com.railinc.jook.service.DowntimeService;
import com.railinc.jook.service.ViewTrackingService;
import com.railinc.jook.web.interactions.DowntimeInteractionFactoryImpl;
import com.railinc.jook.web.util.StandardController;

@Controller
@RequestMapping("secure/downtimes")
public class DowntimeController extends StandardController {

	
	ViewTrackingService viewTracking;
	
	public ViewTrackingService getViewTracking() {
		return viewTracking;
	}




	public void setViewTracking(ViewTrackingService viewTracking) {
		this.viewTracking = viewTracking;
	}




	public DowntimeService getService() {
		return service;
	}


	

	public void setService(DowntimeService service) {
		this.service = service;
	}

	DowntimeService service;
	

	@InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm"), false));
    }

	@RequestMapping("")
	public String list() {
		return "redirect:downtimes/list";
	}

	@RequestMapping("list")
	public String list(ModelMap model, @RequestParam(required=false,value="q") String queryString) {
		List<Downtime> findDowntimes = getService().getDowntime(0, Integer.MAX_VALUE, null, true);
		model.addAttribute("downtimes", findDowntimes);
		return ".view.downtimes.list";
	}
	
	

	@RequestMapping(value="submit",params="_eventId_delete")
	public String configurationDelete(Downtime downtime, ModelMap map, HttpServletRequest request) {
		getService().delete(downtime);
		message(request, String.format("Successfully deleted the downtime record"));

		return "redirect:list";
	}
	
	
	
	
	
	@RequestMapping(value="update",params="!id")
	public String newDowntimeForm(ModelMap map) {
		Downtime configurationValue = new Downtime();
		map.addAttribute("downtime", configurationValue);
		return ".view.downtimes.update";
	}

	@RequestMapping("update")
	public String updateDowntimeForm(Downtime value, ModelMap map) {
		Downtime configurationValue = getService().getDowntimeById(value.getId());
		map.addAttribute("downtime", configurationValue);
		return ".view.downtimes.update";
	}

	@RequestMapping(value="submit",params="_eventId_cancel")
	public String cancelUpdateForm(ModelMap map, HttpServletRequest request) {
		message(request,"No action has been taken");
		return "redirect:list";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="submit",params="_eventId_save")
	public String save(@ModelAttribute("downtime") Downtime value, BindingResult result, ModelMap map, HttpServletRequest request) {

		ValidationUtils.rejectIfEmpty(result, "moduleId", "required");
		ValidationUtils.rejectIfEmpty(result, "durationInMinutes", "required");
		ValidationUtils.rejectIfEmpty(result, "startTime", "required");
		ValidationUtils.rejectIfEmpty(result, "title", "required");
		ValidationUtils.rejectIfEmpty(result, "htmlContent", "required");
		if (result.hasErrors()) {
			return ".view.downtimes.update";
		}
		try {
			getService().update(value);
			message(request, String.format("Successfully saved the downtime record"));
			if (viewTracking != null) {
				viewTracking.resetViewState(DowntimeInteractionFactoryImpl.VIEWTRACKING_APPNAME, value.getId());
			}
		} catch (DataIntegrityViolationException cve) {
			map.addAttribute("downtime", value);
			result.rejectValue("key", "data.integrity.downtime.key.duplicated");
			return ".view.downtimes.update"; 
		}
		return "redirect:list";
	}


	

}
