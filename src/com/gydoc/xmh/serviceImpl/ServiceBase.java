package com.gydoc.xmh.serviceImpl;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

/**
 *
 */
public abstract class ServiceBase {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCS() {
        return getSessionFactory().getCurrentSession();
    }
    
}
