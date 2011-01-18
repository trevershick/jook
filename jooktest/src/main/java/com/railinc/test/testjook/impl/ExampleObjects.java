package com.railinc.test.testjook.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class ExampleObjects implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -725288213151641117L;


    protected List<ExampleObject> exampleObjects = new ArrayList<ExampleObject>();

	public boolean add(ExampleObject arg0) {
		return exampleObjects.add(arg0);
	}
	
	public void setExampleObjects(List<ExampleObject> values) {
		if (values != null) {
			exampleObjects.clear();
			exampleObjects.addAll(values);
		}
	}
	public List<ExampleObject> getExampleObjects() {
		return exampleObjects;
	}

	public void addAll(List<ExampleObject> objs) {
		this.exampleObjects.addAll(objs);
		
	}

	
}
