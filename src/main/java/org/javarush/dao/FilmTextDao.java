package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.FilmText;

public class FilmTextDao extends GenericDAO<FilmText> {
    public FilmTextDao(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
