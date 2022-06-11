package ru.gb.lesson6_homework.repositories;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gb.lesson6_homework.SessionFactoryBuilder;
import ru.gb.lesson6_homework.models.Order;
import java.util.List;

@Repository
public class OrderDao {
    private SessionFactoryBuilder sessionFactoryBuilder;

    @Autowired
    public OrderDao(SessionFactoryBuilder sessionFactoryBuilder) {
        this.sessionFactoryBuilder = sessionFactoryBuilder;
    }


    public Order getOrderById(Long id) {
        try (Session session = sessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            return session.createNamedQuery("Order.getById", Order.class).setParameter("id", id).getSingleResult();
        }
    }

    public List<Order> getAllOrders() {
        try (Session session = sessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            return session.createNamedQuery("Order.getAll", Order.class).getResultList();
        }
    }

    public void deleteOrderById(Long id) {
        try (Session session = sessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Order o WHERE o.id = :id").setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void addOrUpdateOrder(Order order) {
        try (Session session = sessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            if (order.getId() == null) {
                session.persist(order);
            } else {
                session.merge(order);
            }
            session.getTransaction().commit();
        }
    }
}
