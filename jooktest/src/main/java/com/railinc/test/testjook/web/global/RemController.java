package com.railinc.test.testjook.web.global;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RemController {
	
	@RequestMapping
	public void database(OutputStream os) throws IOException {
		
		
	}
	
	
	// /testsupport/main/global/rem/component2
	@RequestMapping
	public void component2(OutputStream os) throws IOException {
		os.write("status=red".getBytes()); 
		
	}
	
	// /testsupport/main/global/rem/components
	@RequestMapping
	public void components(OutputStream os) throws IOException {
		// return a list of components
		os.write("database,component2".getBytes()); 
	}

	/**
	 * @param os
	 * @throws IOException
	 */
	@RequestMapping
	public void rem(OutputStream os) throws IOException {
		components(os);
	}



}
