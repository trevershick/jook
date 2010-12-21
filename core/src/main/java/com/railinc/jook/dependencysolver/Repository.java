package com.railinc.jook.dependencysolver;

import java.util.List;

public interface Repository {
	public List<VersionNumber> versionsAvailableForLibrary(String name);
	public void addLibary(Library lib);
	
	
}
