package ru.dsoshnev.hibernateapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateApp {
    public static void main(String[] args) {
        PrepareData.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/hibernatecfg.xml")
                .buildSessionFactory();

        try (Session session = factory.getCurrentSession()) {;
            session.beginTransaction();
            Product product = session.get(Product.class, 1L);
            System.out.println(product);
            session.getTransaction().commit();
        }

        factory.close();
    }
}
