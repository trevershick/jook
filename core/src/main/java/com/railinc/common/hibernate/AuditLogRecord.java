package com.railinc.common.hibernate;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;

import com.railinc.jook.domain.DomainObject;

public class AuditLogRecord extends DomainObject {

	private Auditable entity;
	private String entityAttribute;
	private String entityId;
	private String entityName;
	private String message;
	private String newValue;
	private String oldValue;
	private String updatedBy;
	private String txRef;

	private Date updatedDate;

	public AuditLogRecord() {
	}

	public AuditLogRecord(String entityId, String entityName,
			String propertyName, Object oldValue, Object newValue,
			String operationType, String actorId, Date transTime, String txRef) {
		this.entityId = entityId;
		this.message = operationType;
		this.entityName = entityName;
		this.entityAttribute = propertyName;
		this.oldValue = String.valueOf(oldValue);
		this.newValue = String.valueOf(newValue);
		this.updatedDate = transTime;
		this.updatedBy = actorId;
		this.txRef = txRef;
	}

	public String getTxRef() {
		return txRef;
	}

	public void setTxRef(String txRef) {
		this.txRef = txRef;
	}

	public Object getEntity() {
		return this.entity;
	}

	public String getEntityAttribute() {
		return entityAttribute;
	}

	public String getEntityId() {
		return entityId;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getMessage() {
		return message;
	}

	public String getNewValue() {
		return newValue;
	}

	public String getOldValue() {
		return oldValue;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setEntity(Auditable newObject) {
		this.entity = newObject;
	}

	public void setEntityAttribute(String fieldName) {
		this.entityAttribute = fieldName;

	}

	public void setEntityId(String persistedObjectId) {
		this.entityId = persistedObjectId;

	}

	public void setEntityName(String className) {
		this.entityName = className;

	}

	public void setMessage(String event) {
		this.message = event;

	}

	public void setNewValue(String string) {
		this.newValue = string;

	}

	public void setOldValue(String string) {
		this.oldValue = string;

	}

	public void setUpdatedBy(String userName) {
		this.updatedBy = userName;
	}

	public void setUpdatedDate(Date date) {
		this.updatedDate = date;

	}

	public String getDiff() {
		diff_match_patch d = new diff_match_patch();
		LinkedList<Diff> diff_main = d.diff_main(getOldValue(), getNewValue());
		return html(diff_main);
	}

	protected String html(List<Diff> diffs) {

		StringBuilder html = new StringBuilder();
		int i = 0;
		for (Diff aDiff : diffs) {
			String text = aDiff.text.replace("&", "&amp;").replace("<", "&lt;")
					.replace(">", "&gt;").replace("\n", "&para;<BR>");
			switch (aDiff.operation) {
			case INSERT:
				html.append("<span class=\"diff_insert\">").append(text).append("</span>");
				break;
			case DELETE:
				html.append("<span class=\"diff_delete\">").append(text).append("</span>");
				break;
			case EQUAL:
				html.append("<span class=\"diff_equal\">").append(text).append("</SPAN>");
				break;
			}
			if (aDiff.operation != diff_match_patch.Operation.DELETE) {
				i += aDiff.text.length();
			}
		}
		return html.toString();

	}

}
