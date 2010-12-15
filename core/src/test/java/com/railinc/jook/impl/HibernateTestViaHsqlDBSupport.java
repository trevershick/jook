package com.railinc.jook.impl;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.classic.Session;
import org.hibernate.dialect.HSQLDialect;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public abstract class HibernateTestViaHsqlDBSupport extends TestCase {

    // RollupDataServiceImpl rollupDataService;

    private SessionFactory sessionFactory;

    private HibernateTemplate hibernateTemplate;




    protected void setUp() throws Exception {
        super.setUp();
//        exampleService = new ExampleServiceImpl();
        // rollupDataService = new RollupDataServiceImpl();

        Configuration configuration = new Configuration();
        configuration.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        configuration.setProperty(Environment.DRIVER, "org.hsqldb.jdbcDriver");
        configuration.setProperty(Environment.URL, "jdbc:hsqldb:mem:" + getClass().getSimpleName());
        configuration.setProperty(Environment.USER, "sa");
        configuration.setProperty(Environment.DIALECT, HSQLDialect.class.getName());
        configuration.setProperty(Environment.SHOW_SQL, "true");
        configuration.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
        configuration.addURL(getClass().getResource("/allHibernateMappings.hbm.xml"));

        sessionFactory = configuration.buildSessionFactory();

        hibernateTemplate = new HibernateTemplate(sessionFactory);
//        exampleService.setHibernateTemplate(hibernateTemplate);
        // rollupDataService.setHibernateTemplate(hibernateTemplate);

        // simulate the open session in view behavior. by associate this session
        // factory and session
        // to the transaction manager, the hibernate template uses this sessiona
        // nd any lazily loaded
        // objects can be utilized after a hibernate template call.
        Session s = sessionFactory.openSession();
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));

    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
        org.hibernate.Session session = holder.getSession();
        session.flush();
        TransactionSynchronizationManager.unbindResource(sessionFactory);
        session.close();
    }

    protected Transaction beginTransaction() {
        return SessionFactoryUtils.getSession(this.sessionFactory, false).beginTransaction();
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
}
