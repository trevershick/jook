package com.railinc.jook.dependencysolver;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VersionRange {
	VersionNumber from;
	boolean fromInclusive;
	
	VersionNumber to;
	boolean toInclusive;
	
	public VersionRange(VersionNumber from, boolean fromInclusive, VersionNumber to, boolean toInclusive) {
		this.from = from;
		this.fromInclusive=fromInclusive;
		this.to = to;
		this.toInclusive = toInclusive;
	}
	
	
	private static final String OPEN_BRACKET = "(\\[|\\()?";
	private static final String CLOS_BRACKET = "(\\]|\\))?";
	private static final String VERSION_NUMBER = "(\\d+(?:\\.\\d+(?:\\.\\d+)?)?)";
	private static final String FULL_PATTERN = "^" + OPEN_BRACKET + "(?:" +VERSION_NUMBER + ")?(,)?(?:" + VERSION_NUMBER + ")?"+ CLOS_BRACKET + "$";
	private static final Pattern pattern = Pattern.compile(FULL_PATTERN);
	
	public static VersionRange parse(String text) throws ParseException {
		Matcher matcher = pattern.matcher(text);
		if (matcher.matches()) {
			String openBracket = matcher.group(1);
			String version1 = matcher.group(2);
			String comma = matcher.group(3);
			String version2 = matcher.group(4);
			String closBracket = matcher.group(5);
			
			if (openBracket == null) {
				openBracket = "[";
			}
			if (closBracket == null) {
				closBracket = "]";
			}
			
			if (comma == null) {
				version2 = version1;
			} else if (version1 == null) {
				version1 = "0.0.0";
			} else if (version2 == null) {
				version2 = String.format("%d.%d.%d", Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
			}
			
			
			if (version1 == null && version2 == null) {
				throw new ParseException("Not a valid range specified, must include at least one version number before or after the comma",0);
			}
			return new VersionRange(VersionNumber.parse(version1), openBracket.equals("["), VersionNumber.parse(version2), closBracket.equals("]"));
		}
		throw new ParseException(text + " is not valid, a valid example is [1.2,1.3)",0);
	}

	public VersionNumber getFrom() {
		return from;
	}

	public boolean isFromInclusive() {
		return fromInclusive;
	}

	public VersionNumber getTo() {
		return to;
	}

	public boolean isToInclusive() {
		return toInclusive;
	}

	public boolean isInRange(VersionNumber actualVersionNumber) {
		actualVersionNumber.assertIsReal();

		if (from.equals(actualVersionNumber) && fromInclusive) {
			return true;
		} 
		if (to.equals(actualVersionNumber) && toInclusive) {
			return true;
		}
		if (from.equals(actualVersionNumber) && !fromInclusive) {
			return false;
		} 
		if (to.equals(actualVersionNumber) && !toInclusive) {
			return false;
		}
		return (from.compareTo(actualVersionNumber) + to.compareTo(actualVersionNumber) == 0);
	}
}
