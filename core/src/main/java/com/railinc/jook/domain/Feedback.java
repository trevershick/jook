package com.railinc.jook.domain;


public class Feedback extends DomainObject {
	String id;
	String ssoModuleId;
	String type;
	String content;
	Long rapidId;
	String ssoUserId;
	String userName;
	
	
//	public Feedback(DBObject o) {
//		id = o.get("_id").toString();
//		ssoModuleId = (String) o.get("ssoModuleId");
//		type = (String) o.get("type");
//		content = (String) o.get("content");
//		rapidId = (Long) o.get("rapidId");
//		ssoUserId = (String) o.get("ssoUserId");
//		userName = (String) o.get("ssoUserName");
//	}
//	
//	public DBObject toMongoObject() {
//		BasicDBObjectBuilder b = new BasicDBObjectBuilder().append("ssoModuleId", ssoModuleId)
//			.append("type", type)
//			.append("content",content)
//			.append("rapidId",rapidId)
//			.append("ssoUserId",ssoUserId)
//			.append("userName",userName);
//		
//		if (id != null) {
//			b.append("_id", new ObjectId(id));
//		}
//		return b.get();
//	}

	
	public String getContent() {
		return content;
	}
	
	
	public Long getRapidId() {
		return rapidId;
	}
	public String getSsoModuleId() {
		return ssoModuleId;
	}
	public String getSsoUserId() {
		return ssoUserId;
	}
	public String getType() {
		return type;
	}
	public String getUserName() {
		return userName;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setRapidId(Long rapidId) {
		this.rapidId = rapidId;
	}
	public void setSsoModuleId(String ssoModuleId) {
		this.ssoModuleId = ssoModuleId;
	}
	public void setSsoUserId(String ssoUserId) {
		this.ssoUserId = ssoUserId;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
