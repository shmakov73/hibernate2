package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.Category;

public class CategoryDao extends GenericDAO<Category> {
    public CategoryDao(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
