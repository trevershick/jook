package com.railinc.jook.domain;

import java.util.Date;

public class DomainObject implements Modifiable {
	private Long id;
	private String creator;
	private Date lastModified;
	private Date created;
	private String lastModifier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainObject other = (DomainObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String getLastModifier() {
		return lastModifier;
	}

	@Override
	public void setLastModifier(String userId) {
		this.lastModifier = userId;
	}

	@Override
	public Date getLastModified() {
		return this.lastModified;
	}

	@Override
	public void setLastModified(Date value) {
		this.lastModified = value;
	}

	@Override
	public String getCreator() {
		return this.creator;
	}

	@Override
	public void setCreator(String userID) {
		this.creator = userID;
	}

	@Override
	public Date getCreated() {
		return this.created;
	}

	@Override
	public void setCreated(Date value) {
		this.created = value;
	}
	
}
