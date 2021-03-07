package ru.dsoshnev.hibernateapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class HibernateApp {

    public static void forcePrepareData(SessionFactory factory) {
        try (Session session = factory.getCurrentSession()) {
            String sql = Files.lines(Paths.get("hibernateapp.sql")).collect(Collectors.joining(" "));
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void insert(SessionFactory factory) {
        try (Session session = factory.getCurrentSession()) {;
            session.beginTransaction();
            Product  product = new Product("Product 7", 70.70D);
            session.save(product);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public static void select(SessionFactory factory) {
        try (Session session = factory.getCurrentSession()) {;
            session.beginTransaction();
            Product product = session.get(Product.class, 1L);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public static void update(SessionFactory factory) {
        try (Session session = factory.getCurrentSession()) {;
            session.beginTransaction();
            Product product = session.get(Product.class, 2L);
            product.setCost(100.20);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public static void delete(SessionFactory factory) {
        try (Session session = factory.getCurrentSession()) {;
            session.beginTransaction();
            Product product = session.get(Product.class, 3L);
            session.delete(product);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {

        try(SessionFactory factory = new Configuration()
                .configure("configs/hibernatecfg.xml")
                .buildSessionFactory()) {
            forcePrepareData(factory);
            insert(factory);
            select(factory);
            update(factory);
            delete(factory);
        }
    }
}
