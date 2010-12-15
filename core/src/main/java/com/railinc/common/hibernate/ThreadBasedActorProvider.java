package com.railinc.common.hibernate;

import org.apache.log4j.MDC;

public class ThreadBasedActorProvider implements ActorProvider {
	final ThreadLocal<String> actors = new ThreadLocal<String>();
	
	public void setActor(String actor) {
		MDC.put("actor", actor);
		actors.set(actor);
	}
	
	public void unsetActor() {
		actors.remove();
	}

	@Override
	public String getCurrentActor() {
		String string = actors.get();
		if (null == string) {
			return "unknown";
		}
		return string;
	}
	
}
