package com.railinc.common.hibernate;

/*
| File:    HibernateAuditLogListener.java
| Created: Mar 3, 2008
| Author:  Will Hoover
*/
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.Initializable;
import org.hibernate.event.PreDeleteEvent;
import org.hibernate.event.PreDeleteEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreLoadEvent;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.proxy.HibernateProxyHelper;
import org.hibernate.type.ImmutableType;
import org.hibernate.type.MutableType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.railinc.jook.domain.Modifiable;
/*http://community.jboss.org/wiki/auditlogging*/
/**
* Audit Log Listener is used to log insert, update, delete, and load operations. Complete list of listeners/events can be obtained at <tt>org.hibernate.event.EventListeners</tt>.
* 
* @see org.hibernate.event.EventListeners
* @author whoover
*/
public final class HibernateModifiableListener implements PreInsertEventListener, PreUpdateEventListener, Initializable {

   /**
	 * 
	 */
	private static final long serialVersionUID = -3339212182246548943L;
	private ActorProvider actorProvider;
   
   protected String getActor() {
	   if (actorProvider == null) {
		   return null;
	   }
	   String tmp = actorProvider.getCurrentActor();
	   if (null==tmp) {
		   tmp = "unknown";
	   }
	   return tmp;
   }
   public ActorProvider getActorProvider() {
	   return actorProvider;
   }
   
	public void setActorProvider(ActorProvider actorProvider) {
		this.actorProvider = actorProvider;
	}
	
   /**
    * {@inheritDoc}
    */
   @Override
   public final void initialize(final Configuration cfg) {
       //
   }


   /**
    * Log insertions made to the current model in the the Audit Trail.
    * 
    * @param event
    *            the pre-insertion event
 * @return 
    */
   @Override
   public final boolean onPreInsert(final PreInsertEvent event) {
	   
	   Object entity = event.getEntity();
	   if (entity instanceof Modifiable) {
		   Modifiable modifiable = (Modifiable) entity;
	       final String actorId = getActor();
		   modifiable.setCreated(new Date());
		   modifiable.setCreator(actorId);
	   }
	   return false;
   }

   /**
    * Log updates made to the current model in the the Audit Trail.
    * 
    * @param event
    *            the pre-update event
    */
   @Override
   public final boolean onPreUpdate(PreUpdateEvent event) {
	   Object entity = event.getEntity();
	   if (entity instanceof Modifiable) {
		   Modifiable modifiable = (Modifiable) entity;
	       final String actorId = getActor();
		   modifiable.setLastModified(new Date());
		   modifiable.setLastModifier(actorId);
	   }
       return false;
   }
   
}

