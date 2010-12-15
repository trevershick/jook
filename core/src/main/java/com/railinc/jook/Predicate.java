package com.railinc.jook;

public interface Predicate<I,O> {
	O call(I o);
}
