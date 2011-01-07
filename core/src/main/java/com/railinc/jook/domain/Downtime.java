package com.railinc.jook.domain;

import java.util.Date;


public class Downtime extends DomainObject {
	Long durationInMinutes = 60L;

	String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	String htmlContent;

	String moduleId;

	Date startTime;

	public Downtime() {
	}

	// public Downtime(DBObject o) {
	// this.moduleId = (String) o.get("moduleId");
	// this.durationInMinutes = Long.parseLong(o.get("durationInMinutes")
	// .toString());
	// this.htmlContent = (String) o.get("htmlContent");
	// this.startTime = null;
	// this.id = o.get("_id").toString();
	// this.startTime = (Date) o.get("startTime");
	// }

	public long getDurationInMinutes() {
		return durationInMinutes;
	}

	public String getHtmlContent() {
		return htmlContent;
	}


	public String getModuleId() {
		return moduleId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setDurationInMinutes(long durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

//	public BasicDBObject toMongoObject() {
//		BasicDBObject basicDBObject = new BasicDBObject();
//		basicDBObject.put("moduleId", moduleId);
//		basicDBObject.put("durationInMinutes", durationInMinutes);
//		basicDBObject.put("htmlContent", htmlContent);
//		basicDBObject.put("startTime", startTime);
//		if (id != null) {
//			basicDBObject.put("_id", new ObjectId(id));
//		}
//		return basicDBObject;
//	}

}
