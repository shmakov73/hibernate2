package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.Country;

public class CountryDao extends GenericDAO<Country> {
    public CountryDao(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
