/**
 * Copyright (c) Railinc Corporation, 2009.
 * All rights reserved.
 */
package com.railinc.jook.web.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author sdtxs01
 *
 */
@Controller
@RequestMapping("secure/support")
public class SupportController extends SupportControllerBaseImpl {
	
	@RequestMapping("snoop")
	public String snoop(HttpServletRequest request) {
		return ".view.support.snoop";
	}
	
	@RequestMapping
	public String list(ModelMap map) {
		return ".view.support";
	}
	
	@RequestMapping("properties")
	public String properties(ModelMap model) {
		model.addAttribute("properties", System.getProperties().entrySet());
		return ".view.support.properties";
	}
	

}
