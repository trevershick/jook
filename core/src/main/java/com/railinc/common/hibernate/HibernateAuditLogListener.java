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
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PreDeleteEvent;
import org.hibernate.event.PreDeleteEventListener;
import org.hibernate.event.PreLoadEvent;
import org.hibernate.event.PreLoadEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.proxy.HibernateProxyHelper;
import org.hibernate.type.ImmutableType;
import org.hibernate.type.MutableType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*http://community.jboss.org/wiki/auditlogging*/
/**
* Audit Log Listener is used to log insert, update, delete, and load operations. Complete list of listeners/events can be obtained at <tt>org.hibernate.event.EventListeners</tt>.
* 
* @see org.hibernate.event.EventListeners
* @author whoover
*/
public final class HibernateAuditLogListener implements
PreDeleteEventListener, PostInsertEventListener, PreUpdateEventListener,
PreLoadEventListener, Initializable {

   private static final long serialVersionUID = 1L;
   private static final Logger LOG = LoggerFactory.getLogger(HibernateAuditLogListener.class);
   public static final String OPERATION_TYPE_INSERT = "INSERT";
   public static final String OPERATION_TYPE_UPDATE = "UPDATE";
   public static final String OPERATION_TYPE_DELETE = "DELETE";

   private TxRefProvider txRefProvider;
   public TxRefProvider getTxRefProvider() {
	return txRefProvider;
}
public void setTxRefProvider(TxRefProvider txRefProvider) {
	this.txRefProvider = txRefProvider;
}

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
    * Log deletions made to the current model in the the Audit Trail.
    * 
    * @param event
    *            the pre-deletion event
    */
   @Override
   public final boolean onPreDelete(final PreDeleteEvent event) {
       try {
           final String actor = getActor();
           final String txref = getTxRef();
           final Serializable entityId = event.getPersister().hasIdentifierProperty() ? event.getPersister().getIdentifier(event.getEntity(), event.getPersister().guessEntityMode(event.getEntity())) : null;
           final String entityName = event.getEntity().getClass().getCanonicalName();
           final Date transTime = new Date(); // new Date(event.getSource().getTimestamp());

           // need to have a separate session for audit save
           StatelessSession session = event.getPersister().getFactory().openStatelessSession();
           session.beginTransaction();

           if (LOG.isDebugEnabled()) {
               LOG.debug("{} for: {}, ID: {}, actor: {}, date: {}", new Object[] { entityName, entityId, actor, transTime });
           }
           session.insert(new AuditLogRecord(entityId.toString(), entityName, OPERATION_TYPE_DELETE, null, null, OPERATION_TYPE_DELETE, actor, transTime, txref));

           session.getTransaction().commit();
           session.close();
       } catch (HibernateException e) {
           LOG.error("Unable to process audit log for DELETE operation", e);
       }
       return false;
   }
   /**
    * Log insertions made to the current model in the the Audit Trail.
    * 
    * @param event
    *            the pre-insertion event
    */
   @Override
   public final void onPostInsert(final PostInsertEvent event) {
       try {
           final String actorId = getActor();
           final String txref = getTxRef();
           
           final String entityId = event.getPersister().hasIdentifierProperty() ? 
        		   String.valueOf(event.getPersister().getIdentifier(event.getEntity(), event.getPersister().guessEntityMode(event.getEntity())))
                    : "";
           final String entityName = event.getEntity().getClass().getCanonicalName();
           final Date transTime = new Date(); // new Date(event.getSource().getTimestamp());
//           final EntityMode entityMode = event.getPersister().guessEntityMode(event.getEntity());
//           Object newPropValue = null;

           // need to have a separate session for audit save
           StatelessSession session = event.getPersister().getFactory().openStatelessSession();
           session.beginTransaction();

//           for (String propertyName : event.getPersister().getPropertyNames()) {
//               newPropValue = event.getPersister().getPropertyValue(event.getEntity(), propertyName, entityMode);
//               // because we are performing an insert we only need to be concerned will non-null values
//               if (newPropValue != null) {
//                   // collections will fire their own events
//                   if (!(newPropValue instanceof Collection)) {
//                       if (LOG.isDebugEnabled()) {
//                           LOG.debug("{} for: {}, ID: {}, property: {}, value: {}, actor: {}, date: {}", new Object[] { OPERATION_TYPE_INSERT, entityName, entityId, propertyName, newPropValue, actorId, transTime });
//                       }
                       session.insert(new AuditLogRecord(entityId, entityName, null, null, null , OPERATION_TYPE_INSERT, actorId, transTime,txref));
//                   }
//               }
//           }

           session.getTransaction().commit();
           session.close();
       } catch (HibernateException e) {
           LOG.error("Unable to process audit log for INSERT operation", e);
       }
   }

   /**
    * Log updates made to the current model in the the Audit Trail.
    * 
    * @param event
    *            the pre-update event
    */
   @Override
   public final boolean onPreUpdate(PreUpdateEvent event) {
       try {
           final String actorId = getActor();
           final String txref = getTxRef();

           final Serializable entityId = event.getPersister().hasIdentifierProperty() ? event.getPersister().getIdentifier(event.getEntity(), event.getPersister().guessEntityMode(event.getEntity()))
                   : null;
           
           final String entityName = event.getEntity().getClass().getCanonicalName();
           final Date transTime = new Date(); // new Date(event.getSource().getTimestamp());
           final EntityMode entityMode = event.getPersister().guessEntityMode(event.getEntity());
           Object oldPropValue = null;
           Object newPropValue = null;

           // need to have a separate session for audit save
           StatelessSession session = event.getPersister().getFactory().openStatelessSession();
           session.beginTransaction();

           // get the existing entity from session so that we can extract existing property values
           Object existingEntity = session.get(event.getEntity().getClass(), entityId);

           // cycle through property names, extract corresponding property values and insert new entry in audit trail
           for (String propertyName : event.getPersister().getPropertyNames()) {
        	   if (propertyName.startsWith("lastModif")) {
        		   continue;
        	   }
               newPropValue = event.getPersister().getPropertyValue(event.getEntity(), propertyName, entityMode);
               // because we are performing an insert we only need to be concerned will non-null values
               if (newPropValue != null) {
                   // collections will fire their own events
                   if (!(newPropValue instanceof Collection)) {
                	   if (existingEntity != null) {
                		   oldPropValue = event.getPersister().getPropertyValue(existingEntity, propertyName, entityMode);
                	   }
                	   
                	   String oldStringValue = toString(event.getPersister(), propertyName, oldPropValue);
                	   String newStringValue = toString(event.getPersister(), propertyName, newPropValue);
                	   if (StringUtils.equals(oldStringValue, newStringValue)) {
                		   continue;
                	   }
                	   
                       //if (EqualsHelper.equals(oldPropValue, newPropValue)) continue;
                       if (LOG.isDebugEnabled()) {
                           LOG.debug("{} for: {}, ID: {}, property: {}, old value: {}, new value: {}, actor: {}, date: {}", new Object[] { OPERATION_TYPE_UPDATE, entityName, entityId, propertyName, oldPropValue, newPropValue, actorId, transTime });
                       }
                       session.insert(new AuditLogRecord(entityId.toString(), entityName, propertyName, 
                    		    oldStringValue, 
                    		    newStringValue,
                    			OPERATION_TYPE_UPDATE, actorId, transTime,txref));
                   }
               }
           }

           session.getTransaction().commit();
           session.close();
       } catch (HibernateException e) {
           LOG.error("Unable to process audit log for UPDATE operation", e);
       }
       return false;
   }
   
   protected String toString(EntityPersister p, String name, Object obj) {
	   if (obj == null) {
		   return null;
	   }
	   
	   Type propertyType = p.getPropertyType(name);
	   if (propertyType instanceof ImmutableType || propertyType instanceof MutableType) {
		   return String.valueOf(obj);
	   }

	   Serializable identifier = p.getIdentifier(obj, EntityMode.POJO);
	   return HibernateProxyHelper.getClassWithoutInitializingProxy(obj).getCanonicalName() + ":" + identifier;
	   
   }

   private String getTxRef() {
	   if (txRefProvider != null) {
		   return txRefProvider.getTxRef();
	   }
	   return null;
   }
/**
    * Log the loading of the current model in the the Audit Trail.
    * 
    * @param event
    *            the pre-load event
    */
   @Override
   public final void onPreLoad(final PreLoadEvent event) {

   }
}

