package com.railinc.jook.dependencysolver;

public class Dependency {
	
	String name;
	VersionRange versionRange;
	
	
	public Dependency(String libraryName, VersionRange range) {
		this.name = libraryName;
		this.versionRange = range;
	}
}
