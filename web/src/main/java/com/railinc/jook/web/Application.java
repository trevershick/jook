package com.railinc.jook.web;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Application {
	private static final String BUNDLE_NAME = "com.railinc.jook.application"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);



	public static String getVersion( ){
		return getString("application.version");
	}
	
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
