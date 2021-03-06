package ru.dsoshnev.hibernateapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PrepareData {
    public static void forcePrepareData() {
        SessionFactory factory = new Configuration()
                .configure("configs/hibernatecfg.xml")
                .buildSessionFactory();
        try (Session session = factory.getCurrentSession()) {
            String sql = Files.lines(Paths.get("hibernateapp.sql")).collect(Collectors.joining(" "));
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }

        factory.close();
    }
}
