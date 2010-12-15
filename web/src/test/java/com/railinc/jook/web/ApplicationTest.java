/**
 * Copyright (c) Railinc Corporation, 2009.
 * All rights reserved.
 */
package com.railinc.jook.web;

import junit.framework.TestCase;

import com.railinc.jook.web.Application;

/**
 * @author sdtxs01
 *
 */
public class ApplicationTest extends TestCase {

	/**
	 * Test method for {@link com.railinc.test105_02_a.web.Application#getVersion()}.
	 */
	public void testGetVersion() {
		String version = Application.getVersion();
		assertNotNull(version);
	}
	
	public void testMissing() {
		assertEquals("!missing.key!", Application.getString("missing.key"));
	}

}
