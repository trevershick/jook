package com.railinc.jook.domain;

import java.util.Date;

public interface Modifiable {
	String getLastModifier();
	void setLastModifier(String userId);
	Date getLastModified();
	void setLastModified(Date value);
	
	String getCreator();
	void setCreator(String userID);
	Date getCreated();
	void setCreated(Date value);
}
