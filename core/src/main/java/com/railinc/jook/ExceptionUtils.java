package com.railinc.jook;

public class ExceptionUtils {
	public static final void argumentCantBeNull(String argumentName, Object value) {
		if (null == value) {
			throw new IllegalArgumentException(argumentName + " cannot be null");
		}
	}
}
