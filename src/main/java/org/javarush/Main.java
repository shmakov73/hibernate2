package org.javarush;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.javarush.dao.*;
import org.javarush.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Main {

    private final SessionFactory sessionFactory;

    private final ActorDao actorDao;
    private final AddressDao addressDao;
    private final CategoryDao categoryDao;
    private final CityDao cityDao;
    private final CountryDao countryDao;
    private final CustomerDao customerDao;
    private final FeatureDao featureDao;
    private final FilmDao filmDao;
    private final FilmTextDao filmTextDao;
    private final InventoryDao inventoryDao;
    private final LanguageDao languageDao;
    private final PaymentDao paymentDao;
    private final RatingDao ratingDao;
    private final RentalDao rentalDao;
    private final StaffDao staffDao;
    private final StoreDao storeDao;


    public Main(){
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movie");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "qwerty");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");

        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Feature.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rating.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .addProperties(properties)
                .buildSessionFactory();

        actorDao = new ActorDao(sessionFactory);
        addressDao = new AddressDao(sessionFactory);
        categoryDao = new CategoryDao(sessionFactory);
        cityDao = new CityDao(sessionFactory);
        countryDao = new CountryDao(sessionFactory);
        customerDao = new CustomerDao(sessionFactory);
        featureDao = new FeatureDao(sessionFactory);
        filmDao = new FilmDao(sessionFactory);
        filmTextDao = new FilmTextDao(sessionFactory);
        inventoryDao = new InventoryDao(sessionFactory);
        languageDao = new LanguageDao(sessionFactory);
        paymentDao = new PaymentDao(sessionFactory);
        ratingDao = new RatingDao(sessionFactory);
        rentalDao = new RentalDao(sessionFactory);
        staffDao = new StaffDao(sessionFactory);
        storeDao = new StoreDao(sessionFactory);
    }



    public static void main(String[] args) {
        Main main = new Main();
        Customer customer = main.createCustomer();

        main.customerReturnInventory();

        main.customerRentInventory(customer);

        main.newMovieAvailable();
    }

    private void newMovieAvailable() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Language language = languageDao.getItems(0, 20).stream().unordered().findAny().get();
            List<Category> categories = categoryDao.getItems(0, 5);
            List<Actor> actors = actorDao.getItems(0, 7);

            Film film = new Film();
            film.setActors(new HashSet<>(actors));
            film.setRating(Rating.PG13);
            film.setSpecialFeatures(Set.of(Feature.TRAILERS, Feature.COMMENTARIES));
            film.setLength((short)123);
            film.setReplacementCost(BigDecimal.valueOf(21));
            film.setLanguage(language);
            film.setRentalRate(BigDecimal.ZERO);
            film.setDescription("film description");
            film.setTitle("title");
            film.setRentalDuration((byte)15);
            film.setOriginalLanguage(language);
            film.setCategories(new HashSet<>(categories));
            film.setYear(Year.now());
            filmDao.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilm(film);
            filmText.setId(film.getId());
            filmText.setDescription("description");
            filmText.setTitle("title");
            filmTextDao.save(filmText);


            session.getTransaction().commit();
        }
    }

    private void customerRentInventory(Customer customer) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Film film = filmDao.getFirstAvailableFilm();
            Store store = storeDao.getItems(0, 1).get(0);

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryDao.save(inventory);

            Staff staff = store.getStaff();
            Rental rental = new Rental();

            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rentalDao.save(rental);

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(15.56));
            payment.setStaff(staff);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setRental(rental);
            paymentDao.save(payment);

            session.getTransaction().commit();
        }
    }

    private void customerReturnInventory() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Rental rental = rentalDao.getUnreturnedRental();
            rental.setReturnDate(LocalDateTime.now());
            rentalDao.save(rental);

            session.getTransaction().commit();
        }
    }

    private Customer createCustomer() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Store store = storeDao.getItems(0, 1).get(0);
            City city = cityDao.getByName("Sharja");

            Address address = new Address();
            address.setAddress("Emirates street 13");
            address.setPhone("+123456789");
            address.setCity(city);
            address.setDistrict("district");
            addressDao.save(address);

            Customer customer = new Customer();
            customer.setActive(true);
            customer.setEmail("email@gmail.com");
            customer.setAddress(address);
            customer.setStore(store);
            customer.setFirstName("Name");
            customer.setLastName("Lastname");
            customerDao.save(customer);

            session.getTransaction().commit();
            return customer;
        }
    }
}