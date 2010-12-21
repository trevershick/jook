package com.railinc.jook.dependencysolver;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionNumber implements
		Comparable<VersionNumber> {
//	public static final int ANY = -1;
	Integer major = 0;
	Integer minor = 0;
	Integer maintenance = 0;

	public void assertIsReal() {
		if (major == -1 || minor == -1 || maintenance == -1) {
			// throw new RuntimeException("fail, this version is not real - " +
			// toString());
		}
	}

	public VersionNumber(int major, int minor, int maintenance) {
		this.major = major;
		this.minor = minor;
		this.maintenance = maintenance;
	}

	public VersionNumber(int major, int minor) {
		this(major, minor, 0);
	}
	public String toString() {
		return new StringBuilder().append(major).append(".").append(minor).append(".").append(maintenance).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((maintenance == null) ? 0 : maintenance.hashCode());
		result = prime * result + ((major == null) ? 0 : major.hashCode());
		result = prime * result + ((minor == null) ? 0 : minor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VersionNumber other = (VersionNumber) obj;
		return compareTo(other) == 0;
	}

	@Override
	public int compareTo(VersionNumber o) {
		if (this == o) {
			return 0;
		}
		if (major.compareTo(o.major) != 0) {
			return major.compareTo(o.major);
		}
		if (minor.compareTo(o.minor) != 0) {
			return minor.compareTo(o.minor);
		}
		return maintenance.compareTo(o.maintenance);
	}


	public static VersionNumber parse(String text) throws ParseException {
		Matcher matcher = pattern.matcher(text);
		if (matcher.matches()) {
			int major = Integer.parseInt(matcher.group(1));
			int minor = matcher.group(2) != null ? Integer.parseInt(matcher
					.group(2)) : 0;
			int maint = matcher.group(3) != null ? Integer.parseInt(matcher
					.group(3)) : 0;
			return new VersionNumber(major, minor, maint);
		}
		throw new ParseException(text + " doesn't match *(.*(.*))",0);
	}

	public static final String VERSION_NUMBER_PATTERN = "(\\d+)\\.(\\d+)(?:\\.(\\d+))?";
	static final Pattern pattern = Pattern.compile("^" + VERSION_NUMBER_PATTERN
			+ "$");
}
