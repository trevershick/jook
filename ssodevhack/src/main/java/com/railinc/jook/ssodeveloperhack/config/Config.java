/*
 * Created on Jan 24, 2006
 *
 */
package com.railinc.jook.ssodeveloperhack.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class manages read only access to configuration properties that are stored in a configuration file. Several Java system properties
 * can be used and are described below. 
 * 
 * com.railinc.sso.rt.debug - set to false by default. If set to true, messages are sent to System.out concerning reloading of properties.
 * 
 * @author rkainz
 */
public class Config
{
	public final static String DEBUG = "com.railinc.sso.rt.debug";
	private static Config INSTANCE = null;
	private Properties properties;
	
	/**
	 * @param properties2
	 */
	private Config(Properties properties2) {
		this.properties = properties2;
	}


	// private boolean isDebugEnabled()
	// {
	// return (Boolean.valueOf(System.getProperty(DEBUG)).booleanValue());
	//	}
	


	public String getProperty (String propertyName)
	{
		return properties.getProperty(propertyName);
	}
	


	/**
	 * @throws IOException 
	 * 
	 */
	public static synchronized Config getInstance()  {
		if (INSTANCE == null) {
			String property = System.getProperty("sso.configuration");
			InputStream resourceAsStream;
			try {
				resourceAsStream = new FileInputStream(property);
			} catch (FileNotFoundException e1) {
				throw new RuntimeException(e1);
			}
			if (resourceAsStream == null) {
				throw new RuntimeException(property + " could not be loaded");
			}
			Properties properties2 = new Properties();
			try {
				properties2.load(resourceAsStream);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			INSTANCE = new Config(properties2);
		}
		return INSTANCE;
	}
}
