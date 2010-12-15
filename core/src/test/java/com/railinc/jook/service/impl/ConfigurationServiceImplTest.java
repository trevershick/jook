package com.railinc.jook.service.impl;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.railinc.jook.domain.ConfigurationValue;
import com.railinc.jook.service.ConfigurationService;

public class ConfigurationServiceImplTest extends TestCase {

	
	private ConfigurationService service;
	public void setUp() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:/testApplicationContext-resources.xml","classpath:jookApplicationContext.xml"});
		service = (ConfigurationService) ctx.getBean("configurationService");
		
	}
	public void testGetConfigurationValues() {
		List<ConfigurationValue> configurationValues = service.getConfigurationValues();
		assertTrue(configurationValues.size() > 0);
	}

}
