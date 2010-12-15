package com.railinc.jook.web.secure;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.railinc.jook.web.util.StandardController;

@Controller
@RequestMapping("secure/refdata")
public class RefDataApplicationsController extends StandardController {
	

	@RequestMapping(method=RequestMethod.GET,value="applications")
	public void applications(HttpServletResponse r) throws IOException {
		PrintWriter writer = r.getWriter();
		writer.write("[ \"EHMS\",\"EMIS\",\"IRFI\" ]");
		writer.close();
	}
	

	
}
