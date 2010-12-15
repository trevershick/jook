package com.railinc.jook.web.global;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"global/home","","global"})
public class HomeController {
	
	

	@RequestMapping
	public String home(ModelMap map) {
		
		return ".view.home";
	}
}
