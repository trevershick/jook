package com.railinc.test.testjook.impl;



public class ExampleObject implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -725288213151641118L;
	
	Long id;
	String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
