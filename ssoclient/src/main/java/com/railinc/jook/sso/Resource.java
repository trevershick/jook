/**
 * Copyright (c) Railinc Corporation, 2009.
 * All rights reserved.
 */
package com.railinc.jook.sso;

import java.io.Serializable;

/**
 * @author sdtxs01
 *
 */
public class Resource implements Comparable<Resource>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5197387139063139295L;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	private String name;
	private String appUrl;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the relativeUrl
	 */
	public String getAppUrl() {
		return appUrl;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param relativeUrl the relativeUrl to set
	 */
	public void setAppUrl(String relativeUrl) {
		this.appUrl = relativeUrl;
	}

	public int compareTo(Resource o) {
		String lhs = name == null ? "" : name.toUpperCase();
		String rhs = o.name == null ? "" : o.name.toUpperCase();
		return lhs.compareTo(rhs);
	}
	
	public String toJSONString() {
		return String.format("{\"name\":\"%s\", \"appUrl\":\"%s\"}", 
				getName(), getAppUrl());
	}
}
