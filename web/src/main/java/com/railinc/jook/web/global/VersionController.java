package com.railinc.jook.web.global;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.railinc.jook.web.Application;

@Controller
@RequestMapping("global/version")
public class VersionController {
	
	
	// /jook/main/global/version
	@RequestMapping
	public void showVersion(OutputStream os) throws IOException {
		os.write(Application.getVersion().getBytes());
	}
}
