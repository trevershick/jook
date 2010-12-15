/**
 * Copyright (c) Railinc Corporation, 2009.
 * All rights reserved.
 */
package com.railinc.jook.web.secure.eizoku;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author sdtxs01
 *
 */
@Controller
@RequestMapping("secure/eizoku")
public class EizokuController  {
	
	@RequestMapping
	public String landing(HttpServletRequest request) {
		return "redirect:eizoku/link/list";
	}
	

}
