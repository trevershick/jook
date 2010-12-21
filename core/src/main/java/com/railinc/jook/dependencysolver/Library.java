package com.railinc.jook.dependencysolver;

import java.util.HashSet;
import java.util.Set;

public class Library {
	String name; // jquery or jquery.ui, facebox, jquery.jgrowl
	VersionNumber version;
	Set<Dependency> dependencies = new HashSet<Dependency>();
	
	public Library(String name,VersionNumber version) {
		this.version = version;
		this.name = name;
	}
	
	public void addDependency(Dependency d) {
		this.dependencies.add(d);
	}
}
