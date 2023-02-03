package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.Actor;

public class ActorDao extends GenericDAO<Actor> {
    public ActorDao(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
