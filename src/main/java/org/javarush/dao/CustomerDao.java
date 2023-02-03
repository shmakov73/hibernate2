package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.Customer;

public class CustomerDao extends GenericDAO<Customer> {
    public CustomerDao(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
