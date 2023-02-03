package org.javarush;

import org.hibernate.Session;
import org.javarush.domain.*;
import org.javarush.service.ConnectionProvider;
import org.javarush.service.DaoProvider;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private final DaoProvider daoProvider = new DaoProvider();

    public static void main(String[] args) {
        Main main = new Main();
        Customer customer = main.createCustomer();

        main.customerReturnInventory();

        main.customerRentInventory(customer);

        main.newMovieAvailable();
    }

    private void newMovieAvailable() {
        try(Session session = ConnectionProvider.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Language language = daoProvider.getLanguageDao().getItems(0, 20).stream().unordered().findAny().get();
            List<Category> categories = daoProvider.getCategoryDao().getItems(0, 5);
            List<Actor> actors = daoProvider.getActorDao().getItems(0, 7);

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
            daoProvider.getFilmDao().save(film);

            FilmText filmText = new FilmText();
            filmText.setFilm(film);
            filmText.setId(film.getId());
            filmText.setDescription("description");
            filmText.setTitle("title");
            daoProvider.getFilmTextDao().save(filmText);


            session.getTransaction().commit();
        }
    }

    private void customerRentInventory(Customer customer) {
        try(Session session = ConnectionProvider.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Film film = daoProvider.getFilmDao().getFirstAvailableFilm();
            Store store = daoProvider.getStoreDao().getItems(0, 1).get(0);

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            daoProvider.getInventoryDao().save(inventory);

            Staff staff = store.getStaff();
            Rental rental = new Rental();

            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            daoProvider.getRentalDao().save(rental);

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(15.56));
            payment.setStaff(staff);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setRental(rental);
            daoProvider.getPaymentDao().save(payment);

            session.getTransaction().commit();
        }
    }

    private void customerReturnInventory() {
        try(Session session = ConnectionProvider.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Rental rental = daoProvider.getRentalDao().getUnreturnedRental();
            rental.setReturnDate(LocalDateTime.now());
            daoProvider.getRentalDao().save(rental);

            session.getTransaction().commit();
        }
    }

    private Customer createCustomer() {
        try(Session session = ConnectionProvider.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Store store = daoProvider.getStoreDao().getItems(0, 1).get(0);
            City city = daoProvider.getCityDao().getByName("Sharja");

            Address address = new Address();
            address.setAddress("Emirates street 13");
            address.setPhone("+123456789");
            address.setCity(city);
            address.setDistrict("district");
            daoProvider.getAddressDao().save(address);

            Customer customer = new Customer();
            customer.setActive(true);
            customer.setEmail("email@gmail.com");
            customer.setAddress(address);
            customer.setStore(store);
            customer.setFirstName("Name");
            customer.setLastName("Lastname");
            daoProvider.getCustomerDao().save(customer);

            session.getTransaction().commit();
            return customer;
        }
    }
}