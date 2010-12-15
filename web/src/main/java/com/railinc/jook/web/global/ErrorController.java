package com.railinc.jook.web.global;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("global/error")
public class ErrorController {
	@RequestMapping
	public void doNothing() {
		
	}
}
