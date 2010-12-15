package com.railinc.jook.web.global;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("global/rem")
public class RemController {
	JdbcTemplate template;
	
	@RequestMapping("database")
	public void database(OutputStream os) throws IOException {
		try {
			JdbcTemplate template2 = this.getTemplate();
			template2.setMaxRows(1);
			template2.execute("SELECT * FROM EXAMPLE_OBJS");
			os.write("status=green".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			os.write("status=red".getBytes());
		}
		
	}
	
	
	// /jook/main/global/rem/component2
	@RequestMapping("component2")
	public void component2(OutputStream os) throws IOException {
		os.write("status=red".getBytes()); 
		
	}
	
	// /jook/main/global/rem/components
	@RequestMapping("components")
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

	public JdbcTemplate getTemplate() {
		return template;
	}


	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}


}
