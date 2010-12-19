package com.railinc.jook.web.secure.jook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("secure/jook")
public class JookController {
	@RequestMapping
	public String home(ModelMap map) {
		
		return ".view.jook.home";
	}
}
