package com.railinc.test.testjook.web.global;

import java.util.Date;

public class News {
	String headline;
	String body;
	Date published = new Date();
	/**
	 * @return the headline
	 */
	public String getHeadline() {
		return headline;
	}
	/**
	 * @return the published
	 */
	public Date getPublished() {
		return published;
	}
	/**
	 * @param published the published to set
	 */
	public void setPublished(Date published) {
		this.published = published;
	}
	/**
	 * @param headline the headline to set
	 */
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
}
