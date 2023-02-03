package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.Language;

public class LanguageDao extends GenericDAO<Language> {
    public LanguageDao(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
