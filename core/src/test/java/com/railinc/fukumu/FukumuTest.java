package com.railinc.fukumu;

import junit.framework.TestCase;

public class FukumuTest extends TestCase {

	public void testIt() {
		
//		Exclude + EHMS = All Apps Except EHMS
//		Exclude + EMIS,EHMS = All Apps Except EHMS and EMIS
//		empty (include) = ALL
//		empty (exclude) = ALL
//		(include) EHMS = Only EHMS
//		(include) EHMS,EMIS = Only EHMS and EMIS
		
		Fukumu<String> f = new Fukumu<String>().withItem("EMIS").withItem("EHMS").includeByDefault();
		assertTrue(f.includes("EMIS"));
		assertTrue(f.includes("EHMS"));
		assertFalse(f.includes("IRF"));
		assertFalse(f.includes(null));
		assertFalse(f.includes(""));

		f = new Fukumu<String>().withItem("EMIS").withItem("EHMS").excludeByDefault();
		assertFalse(f.includes("EMIS"));
		assertFalse(f.includes("EHMS"));
		assertTrue(f.includes("IRF"));
		assertTrue(f.includes(null));
		assertTrue(f.includes(""));
		
	}
}
