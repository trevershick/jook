package com.railinc.fukumu;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Fukumu<T> {
	Set<T> theSet = new HashSet<T>();
	boolean includeByDefault = true;
	

	public Fukumu() {
		
	}
	
	public Fukumu(Set<T> applications) {
		if (applications != null) {
			theSet.addAll(applications);
		}
	}

	/**
	 * 
	 * @param item
	 * @return
	 */
	public boolean includes(T item) {
		if (theSet.size() == 0) return true;
		if (includeByDefault) {
			return theSet.contains(item);
		} else {
			return ! theSet.contains(item);
		}
	}
	
	public Fukumu<T> includeByDefault(boolean trueIsIncludeFalseIsExclude) {
		this.includeByDefault = trueIsIncludeFalseIsExclude;
		return this;
	}
	
	public Fukumu<T> includeByDefault() {
		this.includeByDefault = true;
		return this;
	}
	
	public Fukumu<T> excludeByDefault() {
		this.includeByDefault = false;
		return this;
	}
	
	public Fukumu<T> withItem(T item) {
		if (item != null) {
			theSet.add(item);
		}
		return this;
	}
	
	public Fukumu<T> withItems(Collection<T> items) {
		if (items != null) {
			theSet.addAll(items);
		}
		return this;
	}
	
	public boolean isIncludeByDefault() {
		return includeByDefault;
	}
	
	public Collection<T> items() {
		return Collections.unmodifiableCollection(theSet);
	}
}
