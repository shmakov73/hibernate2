package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.Payment;

public class PaymentDao extends GenericDAO<Payment> {
    public PaymentDao(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
