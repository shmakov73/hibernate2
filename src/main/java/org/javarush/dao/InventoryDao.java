package org.javarush.dao;

import org.hibernate.SessionFactory;
import org.javarush.domain.Inventory;

public class InventoryDao extends GenericDAO<Inventory> {
    public InventoryDao(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
