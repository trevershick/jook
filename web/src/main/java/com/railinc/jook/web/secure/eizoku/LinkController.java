/**
 * Copyright (c) Railinc Corporation, 2009.
 * All rights reserved.
 */
package com.railinc.jook.web.secure.eizoku;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.railinc.jook.domain.Link;
import com.railinc.jook.service.LinkService;
import com.railinc.jook.web.util.StandardController;

/**
 * @author sdtxs01
 *
 */
@Controller
@RequestMapping("secure/eizoku/link")
public class LinkController extends StandardController {
	LinkService service;
	
	Logger log = Logger.getLogger(LinkController.class);
	
	
	
	
	public LinkService getService() {
		return service;
	}
	
	

	public void setService(LinkService service) {
		this.service = service;
	}


	@RequestMapping(value="update",params="_eventId_delete")
	public String deleteLink(@RequestParam(value="path",required=true) String key, HttpServletRequest r, ModelMap map) {
		getService().removeLink(key);
		
		message(r, String.format("Successfully deleted the link"));
		
		return listLinks(map);
	}
	
	
	
	
	@RequestMapping(method=RequestMethod.GET, value="update")
	public String updateALink(@RequestParam(value="k",required=true) String key, ModelMap map) {

		Link Link = getService().getLink(key);
		map.addAttribute("link", Link);
		return ".view.eizoku.link.update";
	}



	@RequestMapping(method=RequestMethod.POST, value="update",params="_eventId_cancel")
	public String cancelUpdate(ModelMap map, HttpServletRequest r) {
		message(r,"No action has been taken");
		return "redirect:list";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="update",params="_eventId_save")
	public String submitUpdate(@ModelAttribute("link") Link value, BindingResult result) {
		ValidationUtils.invokeValidator(new LinkValidator(), value, result);
		
        if (result.hasErrors()) {
            return ".view.eizoku.link.update";
	    } else {
			getService().updateLink(value.getPath(), value.getUrl(), value.getDescription());
			return "redirect:list";
        }
	}

	
	
	
	
	
	@RequestMapping(method=RequestMethod.GET, value="create")
	public String createALink(ModelMap map) {
		Link Link = new Link();
		map.addAttribute("link", Link);
		return ".view.eizoku.link.create";
	}

	
	@RequestMapping(method=RequestMethod.POST, value="create",params="_eventId_cancel")
	public String cancelCreate(ModelMap map,HttpServletRequest r) {
		message(r,"No action has been taken");
		return "redirect:list";
	}
	
	
	
	
	@RequestMapping(method=RequestMethod.POST,value="create",params="_eventId_save")
	public String submitCreate(@ModelAttribute("link") Link value, BindingResult result) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (StringUtils.isBlank(value.getPath())) {
			String nextPath = findNextPath(value);
			value.setPath(nextPath);
		}
		
		ValidationUtils.invokeValidator(new LinkValidator(), value, result);
		if (service.getLink(value.getPath()) != null) {
			result.reject("duplicate.path", new Object[]{value.getPath()}, null);
		}
        if (result.hasErrors()) {
            return ".view.eizoku.link.create";
	    } else {
	    	getService().createLink(value.getPath(), value.getUrl(), value.getDescription());
	    	return "redirect:list";
        }
	}

	
	private String findNextPath(Link i) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String randomKey = null;
		while (service.getLink(randomKey = i.generatePath()) != null);
		return randomKey;
	}
	
	
	
	
	@RequestMapping("list")
	public String listLinks(ModelMap map) {
		log.info("Loaded Links List");
		map.addAttribute("links",getService().getLinks());
		return ".view.eizoku.links";
	}	
}
