package com.railinc.jook.domain;

import java.util.Date;

public class NewsItem extends DomainObject {
	String title;
	String link;
	String description;
	String moduleId;
	Date launchDate;
	Date expirationDate;
	Date authoredDate;
	boolean published;

	public Date getAuthoredDate() {
		return authoredDate;
	}
	public void setAuthoredDate(Date authoredDate) {
		this.authoredDate = authoredDate;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public Date getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	
	
}
