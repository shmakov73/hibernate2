package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.Feature;

public class FeatureDao extends GenericDAO<Feature> {
    public FeatureDao(SessionFactory sessionFactory) {
        super(Feature.class, sessionFactory);
    }
}
