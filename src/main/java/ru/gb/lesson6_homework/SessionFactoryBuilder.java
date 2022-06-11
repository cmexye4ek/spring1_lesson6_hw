package ru.gb.lesson6_homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
public class SessionFactoryBuilder {
    private SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    private void init() {
        sessionFactory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
        session = null;
        try {
            String sql = Files.lines(Paths.get("newBase.sql")).collect(Collectors.joining(" "));
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session  != null) {
                session.close();
            }
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
