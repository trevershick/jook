package com.railinc.test.testjook.web.global;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.railinc.test.testjook.web.Application;

@Controller
public class VersionController {
	
	
	// /testsupport/main/global/version
	@RequestMapping
	public void showVersion(OutputStream os) throws IOException {
		os.write(Application.getVersion().getBytes());
	}
}
