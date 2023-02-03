package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.Store;

public class StoreDao extends GenericDAO<Store> {
    public StoreDao(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
