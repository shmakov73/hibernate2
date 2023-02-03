package org.javarush.service;

import org.hibernate.SessionFactory;
import org.javarush.dao.*;

public class DaoProvider {


    private final ActorDao actorDao;
    private final AddressDao addressDao;
    private final CategoryDao categoryDao;
    private final CityDao cityDao;
    private final CustomerDao customerDao;
    private final FilmDao filmDao;
    private final FilmTextDao filmTextDao;
    private final InventoryDao inventoryDao;
    private final LanguageDao languageDao;
    private final PaymentDao paymentDao;
    private final RentalDao rentalDao;
    private final StoreDao storeDao;

    public DaoProvider() {
        SessionFactory session = ConnectionProvider.getSessionFactory();
        actorDao = new ActorDao(session);
        addressDao = new AddressDao(session);
        categoryDao = new CategoryDao(session);
        cityDao = new CityDao(session);
        customerDao = new CustomerDao(session);
        filmDao = new FilmDao(session);
        filmTextDao = new FilmTextDao(session);
        inventoryDao = new InventoryDao(session);
        languageDao = new LanguageDao(session);
        paymentDao = new PaymentDao(session);
        rentalDao = new RentalDao(session);
        storeDao = new StoreDao(session);
    }

    public ActorDao getActorDao() {
        return actorDao;
    }

    public AddressDao getAddressDao() {
        return addressDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public FilmDao getFilmDao() {
        return filmDao;
    }

    public FilmTextDao getFilmTextDao() {
        return filmTextDao;
    }

    public InventoryDao getInventoryDao() {
        return inventoryDao;
    }

    public LanguageDao getLanguageDao() {
        return languageDao;
    }

    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public RentalDao getRentalDao() {
        return rentalDao;
    }

    public StoreDao getStoreDao() {
        return storeDao;
    }
}
