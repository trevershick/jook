package com.railinc.test.testjook.impl;

import java.util.ArrayList;
import java.util.List;

import com.railinc.test.testjook.service.ExampleService;

public class ExampleServiceImpl implements ExampleService {
	List<ExampleObject> dataSource = new ArrayList<ExampleObject>();

	public ExampleObjects query(String text) {
		ExampleObjects ret = new ExampleObjects();
		for (ExampleObject eo : dataSource) {
			if (eo.getText().contains(text)) {
				ret.add(eo);
			}
		}
		return ret;
	}

	public ExampleObject findByKey(Long key) {
		for (ExampleObject eo : dataSource) {
			if (eo.getId().equals(key)) {
				return eo;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ExampleObjects findByPath() {
		
		
		
		


		if (this.dataSource.size() == 0) {
			for (int i = 0; i < 50; i++) {
				ExampleObject exampleObject = new ExampleObject();
				exampleObject.setText("Object # " + i);
				this.dataSource.add(exampleObject);
			}
		}
		ExampleObjects eos = new ExampleObjects();
		eos.addAll(this.dataSource);

		return eos;
	}

}
