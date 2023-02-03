package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.Address;

public class AddressDao extends GenericDAO<Address> {
    public AddressDao(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
