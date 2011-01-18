package com.railinc.test.testjook.service;

import com.railinc.test.testjook.impl.ExampleObject;
import com.railinc.test.testjook.impl.ExampleObjects;

public interface ExampleService {

	public ExampleObjects query(String text);
	
	public ExampleObjects findByPath();
	
	

	public ExampleObject findByKey(Long key);
	
	
}
