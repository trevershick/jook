package com.railinc.test.testjook.web;

import junit.framework.TestCase;

import org.springframework.ui.ModelMap;

import com.railinc.test.testjook.web.secure.ExampleController;

public class ExampleControllerTest extends TestCase {
	ExampleController controller = null;

	
	
	
	public void testCreate() {
		ModelMap mm = new ModelMap();
		String create = controller.create(mm);
		assertNotNull(create);
		assertEquals(".view.exampleView", create);
		assertNotNull(mm.get("exampleObject"));
	}
	
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		controller = new ExampleController();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
