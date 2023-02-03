package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.Rating;

public class RatingDao extends GenericDAO<Rating> {
    public RatingDao(SessionFactory sessionFactory) {
        super(Rating.class, sessionFactory);
    }
}
