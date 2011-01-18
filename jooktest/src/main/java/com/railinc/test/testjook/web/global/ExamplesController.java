package com.railinc.test.testjook.web.global;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExamplesController {
	
	@RequestMapping
	protected void doNothing(ModelMap model) {
		
	}

}
