package com.railinc.jook.web.util;

public class HighlightFunction {
	public static String highlight(String text, String whatToHighlight) {
		if (null == whatToHighlight || whatToHighlight.length() == 0) {
			return text;
		}
		return text.replaceAll("(?i)(" + whatToHighlight + ")", "<span class=\"highlight\">$0</span>");
	}
}
