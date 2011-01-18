package com.railinc.test.testjook.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnotherExampleController {
	@RequestMapping("/explicitlyMapped")
	public String thisIsMappedByExplicitRequestMapping(@RequestParam(required=false,value="value") String xxx, ModelMap mm) {
		mm.addAttribute("controllerClassName", getClass().getName());
		// do nothing here...
		return ".view.explicitlyMapped";
	}
}
