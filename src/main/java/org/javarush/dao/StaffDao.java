package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.Staff;

public class StaffDao extends GenericDAO<Staff> {
    public StaffDao(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
