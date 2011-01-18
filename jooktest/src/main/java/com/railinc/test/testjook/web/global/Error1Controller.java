package com.railinc.test.testjook.web.global;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Error1Controller {
	
	@RequestMapping
	public void doNothing(HttpServletResponse response) {
		throw new RuntimeException("Test Data Access Exception");
	}
}
