package ru.gb.lesson6_homework.repositories;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gb.lesson6_homework.SessionFactoryBuilder;
import ru.gb.lesson6_homework.models.Consumer;

import java.util.List;

@Repository
public class ConsumerDao {
    private SessionFactoryBuilder sessionFactoryBuilder;

    @Autowired
    public ConsumerDao(SessionFactoryBuilder sessionFactoryBuilder) {
        this.sessionFactoryBuilder = sessionFactoryBuilder;
    }


    public Consumer getConsumerById(Long id) {
        try (Session session = sessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            return session.createNamedQuery("Consumer.getById", Consumer.class).setParameter("id", id).getSingleResult();
        }
    }

    public List<Consumer> getAllConsumers() {
        try (Session session = sessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            return session.createNamedQuery("Consumer.getAll", Consumer.class).getResultList();
        }
    }

    public void deleteConsumerById(Long id) {
        try (Session session = sessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Consumer s WHERE s.id = :id").setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void addOrUpdateConsumer(Consumer consumer) {
        try (Session session = sessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            if (consumer.getId() == null) {
                session.persist(consumer);
            } else {
                session.merge(consumer);
            }
            session.getTransaction().commit();
        }
    }
}
