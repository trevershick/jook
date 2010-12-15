package com.railinc.jook.domain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

public class Link extends DomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -822470203500087179L;
	String path;
	String url;
	String description;

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String key) {
		this.path = key;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String stringValue) {
		this.url = stringValue;
	}

	/**
	 * 
	 * @return true if a generated
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public final String generatePath() throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		MessageDigest digest = MessageDigest.getInstance("SHA");

		byte[] digested = digest.digest((Long.toString(
				System.currentTimeMillis(), 32) + url).getBytes("UTF-8"));
		long l = 0L;
		int mplier = 1;
		int i = 0;
		while (i < digested.length && l < Long.MAX_VALUE) {
			l += mplier * Math.abs(digested[i++]);
			mplier = (int) Math.pow(10, i);
		}
		return Long.toString(l, 32);

	}
}
