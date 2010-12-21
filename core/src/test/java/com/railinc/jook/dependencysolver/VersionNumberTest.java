package com.railinc.jook.dependencysolver;


import java.text.ParseException;

import junit.framework.TestCase;

public class VersionNumberTest extends TestCase {

	public void test_compareTo() {
		VersionNumber v1 = new VersionNumber(10, 2, 3);
		VersionNumber v2 = new VersionNumber(10, 2, 3);
		VersionNumber v3 = new VersionNumber(10, 2, 4);
		
		assertEquals(v1,v2);
		assertEquals(1, v3.compareTo(v1));
		assertEquals(-1, v1.compareTo(v3));
		assertEquals(0, v1.compareTo(v2));
	}
	
	
	
	public void test_parse() throws ParseException {
		assertEquals(new VersionNumber(10, 2, 4), VersionNumber.parse("10.2.4"));
		assertEquals(new VersionNumber(10, 0, 4), VersionNumber.parse("10.0.4"));
		assertEquals(new VersionNumber(10, 0, 0), VersionNumber.parse("10.0"));
		assertEquals(new VersionNumber(10, 2, 0), VersionNumber.parse("10.2"));
		assertEquals(new VersionNumber(0, 2, 0), VersionNumber.parse("0.2"));
		
		
	}


	public void test_range1() throws ParseException {
		VersionRange range = VersionRange.parse("[1.2,1.3)");
		assertEquals(VersionNumber.parse("1.2"), range.from);
		assertEquals(VersionNumber.parse("1.3"), range.to);
		assertTrue(range.isFromInclusive());
		assertFalse(range.isToInclusive());
		
		assertTrue(range.isInRange(VersionNumber.parse("1.2")));
		assertTrue(range.isInRange(VersionNumber.parse("1.2.3")));
		assertFalse(range.isInRange(VersionNumber.parse("1.3")));

		range = VersionRange.parse("(1.2,1.5]"); // 1.2.1 valid - no 1.3 is though http://docs.codehaus.org/display/MAVEN/Dependency+Mediation+and+Conflict+Resolution#DependencyMediationandConflictResolution-DependencyVersionRanges
		assertFalse(range.isInRange(VersionNumber.parse("1.2")));
		assertFalse(range.isInRange(VersionNumber.parse("1.5.6")));
		assertTrue(range.isInRange(VersionNumber.parse("1.3")));

	}
	
	
//	see http://docs.codehaus.org/display/MAVEN/Dependency+Mediation+and+Conflict+Resolution
	public void test_from_maven_spec() throws ParseException {
		VersionRange range = null;
		//		(,1.0]	 x <= 1.0
		range = VersionRange.parse("(,1.0]");
		assertFalse(range.isInRange(new VersionNumber(1,1)));

		assertTrue(range.isInRange(new VersionNumber(0,92)));
		assertTrue(range.isInRange(new VersionNumber(0,1)));
		assertTrue(range.isInRange(new VersionNumber(0,0,1)));
		
		

		//		[1.0]	 Hard requirement on 1.0
		range = VersionRange.parse("[1.0]");
		assertFalse(range.isInRange(new VersionNumber(0,9,9)));
		assertTrue(range.isInRange(new VersionNumber(1,0)));
		assertFalse(range.isInRange(new VersionNumber(1,0,1)));
		
		
		
//		[1.2,1.3]	 1.2 <= x <= 1.3
		range = VersionRange.parse("[1.2,1.3]");
		assertTrue(range.isInRange(new VersionNumber(1,2)));
		assertTrue(range.isInRange(new VersionNumber(1,2,1)));
		assertTrue(range.isInRange(new VersionNumber(1,3)));
		
		assertFalse(range.isInRange(new VersionNumber(1,3,1)));

		
		//		[1.0,2.0)	 1.0 <= x < 2.0
		range = VersionRange.parse("[1.0,2.0)");
		assertTrue(range.isInRange(new VersionNumber(1,0)));
		assertTrue(range.isInRange(new VersionNumber(1,1)));
		assertTrue(range.isInRange(new VersionNumber(1,0,1)));
		
		assertFalse(range.isInRange(new VersionNumber(2,0)));
		assertFalse(range.isInRange(new VersionNumber(2,0,1)));
		
//		[1.5,)	 x >= 1.5
		range = VersionRange.parse("[1.5,)");
		assertFalse(range.isInRange(new VersionNumber(1,4)));
		assertTrue(range.isInRange(new VersionNumber(1,5)));
		assertTrue(range.isInRange(new VersionNumber(9,9)));
		
		range = VersionRange.parse("1.5");
		assertFalse(range.isInRange(new VersionNumber(1,5,1)));
		assertFalse(range.isInRange(new VersionNumber(1,6)));
		assertTrue(range.isInRange(new VersionNumber(1,5)));
		
	}
		
}
