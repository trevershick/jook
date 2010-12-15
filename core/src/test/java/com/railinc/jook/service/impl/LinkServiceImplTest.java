package com.railinc.jook.service.impl;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.railinc.jook.domain.Link;
import com.railinc.jook.service.LinkService;

public class LinkServiceImplTest extends TestCase {

	
	private LinkService service;
	public void setUp() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:/testApplicationContext-resources.xml","classpath:jookApplicationContext.xml"});
		service = (LinkService) ctx.getBean("linkService");
		
	}
	public void testGetConfigurationValues() {
		service.createLink("/test", "http://test.com","no description");
		List<Link> configurationValues = service.getLinks();
		assertTrue(configurationValues.size() > 0);
	}

}
