package com.railinc.test.testjook.web.secure;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.railinc.test.testjook.impl.ExampleObject;
import com.railinc.test.testjook.service.ExampleService;

@Controller
public class ExampleController {
	ExampleService service;

	public ExampleService getExampleService() {
		return service;
	}

	public void setExampleService(ExampleService s) {
		this.service = s;
	}
	
	@RequestMapping
	public String query(ModelMap model) {
		return ".view.queryForm";
	}
	@RequestMapping(params={"q"}) 
	public String query(@RequestParam(value="q",required=true) String query, ModelMap model) {
		// x5258
		model.addAttribute("results", service.query(query));
		return ".view.queryForm";
	}
	/**
	 * This ends up being the default
	 * @param map
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String justExampleSlash(ModelMap map) {
		ExampleObject e = new ExampleObject();
		e.setId(3L);
		e.setText("Handled by protected String justExampleSlash(ModelMap map)");
		map.addAttribute("exampleObject", e);

		return ".view.exampleView";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(ModelMap map) {
		map.addAttribute("results", service.findByPath());
		
		return ".view.exampleList";
	}

	@RequestMapping
	public String create(ModelMap model) {
		assert model != null;
		ExampleObject e = new ExampleObject();
		e.setId(2L);
		e.setText("Handled By protected String create(ModelMap model)");
		model.addAttribute("exampleObject", e);

		return ".view.exampleView";
	}
	
	@RequestMapping
	public String view(@RequestParam("id") Long id, ModelMap model) {
		assert model != null;
		ExampleObject e = new ExampleObject();
		e.setId(id);
		e.setText("Handled By protected String view(String id, ModelMap model)");
		model.addAttribute("exampleObject", e);

		return ".view.exampleView";
	}

}
