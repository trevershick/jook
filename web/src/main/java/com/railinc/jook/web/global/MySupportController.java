package com.railinc.jook.web.global;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.PropertyValues;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.railinc.jook.domain.Incident;
import com.railinc.jook.service.MySupportService;
import com.railinc.jook.web.util.UserAgentStringInfoProvider;

@Controller
@SessionAttributes(value={"incident","lock"})
public class MySupportController {
	
	private MySupportService service = null;
	
	@RequestMapping("/landing")
	public String landing(HttpServletRequest request, Model model) {
		
		Incident incident = (Incident) request.getSession().getAttribute("incident");
		if (null == incident) {
			incident = new Incident();
		}
		
		WebDataBinder binder = new WebDataBinder(incident);
		PropertyValues propertyValues = new ServletRequestParameterPropertyValues(request);
		binder.bind(propertyValues);
		getService().saveIncident(incident);
		model.addAttribute("lock", "SemaphoreI'mReallyNeeded");
		model.addAttribute("incident", incident);
		return "landing";
	}

	public MySupportService getService() {
		return service;
	}

	public void setService(MySupportService service) {
		this.service = service;
	}


	@RequestMapping("/createRapidCase")
	public String createRapidCase(@ModelAttribute("incident") Incident i,@ModelAttribute("lock") Object lock, SessionStatus status) {
		synchronized (lock) {
			
			if (status.isComplete()) {
				return "rapidCaseAlreadyCreated";
			} else {
				i.setRapidCaseId(6000 + i.getId());
				getService().saveIncident(i);
				status.setComplete();
				return "createRapidCase";
			}
		}
	}
	
	@RequestMapping("/screenResolution")
	public String screenResolution(@ModelAttribute("incident") Incident i,
			@ModelAttribute("lock") Object lock,
			@RequestParam(value="x",required=true) Integer x,
			@RequestParam(value="y",required=true) Integer y) {
		synchronized(lock) {
			i.setScreenWidth(x);
			i.setScreenHeight(y);
			getService().saveIncident(i);
		}
		return "screenResolution";
	}

	
	@RequestMapping("/browserSize")
	public String browserSize(@ModelAttribute("incident") Incident i,
			@ModelAttribute("lock") Object lock,
			@RequestParam(value="x",required=true) Integer x,
			@RequestParam(value="y",required=true) Integer y) {
		synchronized(lock) {
			i.setBrowserWidth(x);
			i.setBrowserHeight(y);
			getService().saveIncident(i);
		}
		return "browserSize";
	}



	@RequestMapping("/ssouser")
	public String ssouser() {
		return "ssouser";
	}
	
	@RequestMapping("/product")
	public String product() {
		return "product";
	}

	@RequestMapping("/requestDetails")
	public String requestDetails() {
		return "requestDetails";
	}
	
	
	
	
	@RequestMapping("/browser")
	public String browser(@ModelAttribute("incident") Incident i,
			@ModelAttribute("lock") Object lock, HttpServletRequest request, Model model) {
		
		synchronized(lock) {
			Properties userAgentInfo = (Properties) request.getSession().getAttribute("userAgentInfo");
			if (userAgentInfo == null) {
				try {
					userAgentInfo = UserAgentStringInfoProvider.getProperties(request.getHeader("User-Agent"));
					request.getSession().setAttribute("userAgentInfo",userAgentInfo);
				} catch (Exception e) {
					userAgentInfo = new Properties();
				} 
			}
			i.setBrowser(userAgentInfo.getProperty("ua_name"));
			getService().saveIncident(i);
		}
		return "browser";
	}


	
	
	@RequestMapping("/os")
	public String operatingSystem(@ModelAttribute("incident") Incident i,
			@ModelAttribute("lock") Object lock, HttpServletRequest request, Model model) {
		
		synchronized(lock) {
			Properties userAgentInfo = (Properties) request.getSession().getAttribute("userAgentInfo");
			if (userAgentInfo == null) {
				try {
					userAgentInfo = UserAgentStringInfoProvider.getProperties(request.getHeader("User-Agent"));
					request.getSession().setAttribute("userAgentInfo",userAgentInfo);
				} catch (Exception e) {
					userAgentInfo = new Properties();
				} 
			}
			i.setOperatingSystem(userAgentInfo.getProperty("os_name"));
			getService().saveIncident(i);
		}
		return "os";
	}


	
	@RequestMapping("/ipAddress")
	public String ipAddress(@ModelAttribute("incident") Incident i,
			@ModelAttribute("lock") Object lock, HttpServletRequest request, Model model) {
		synchronized(lock) {
			i.setIpAddress(request.getRemoteAddr().toString());
			getService().saveIncident(i);
		}
		return "ipAddress";
	}
	
	
	@RequestMapping("/flashVersion")
	public String flashVersion(@ModelAttribute("incident") Incident i,
			@ModelAttribute("lock") Object lock,
			@RequestParam(value="v",required=true) String version) {
		synchronized(lock) {
			i.setFlashVersion(version);
			getService().saveIncident(i);
		}
		return "flashVersion";
	}

	
	@RequestMapping("/cookiesEnabled")
	public String cookiesEnabled(@ModelAttribute("incident") Incident i,
			@ModelAttribute("lock") Object lock,
			@RequestParam(value="enabled",required=true) Boolean enabled) {
		synchronized(lock) {
			i.setCookiesEnabled(enabled);
			getService().saveIncident(i);
		}
		return "cookiesEnabled";
	}

}