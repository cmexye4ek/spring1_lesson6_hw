package ru.gb.lesson6_homework.repositories;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gb.lesson6_homework.SessionFactoryBuilder;
import ru.gb.lesson6_homework.models.Product;

import java.util.List;

@Repository
public class ProductDao {
    private SessionFactoryBuilder sessionFactoryBuilder;

    @Autowired
    public ProductDao(SessionFactoryBuilder sessionFactoryBuilder) {
        this.sessionFactoryBuilder = sessionFactoryBuilder;
    }


    public Product getProductById(Long id) {
        try (Session session = sessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            return session.createNamedQuery("Product.getById", Product.class).setParameter("id", id).getSingleResult();
        }
    }

    public List<Product> getAllProducts() {
        try (Session session = sessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            return session.createNamedQuery("Product.getAll", Product.class).getResultList();
        }
    }

    public void deleteProductById(Long id) {
        try (Session session = sessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Product p WHERE p.id = :id").setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void addOrUpdateProduct(Product product) {
        try (Session session = sessionFactoryBuilder.getSessionFactory().openSession()) {
            session.beginTransaction();
            if (product.getId() == null) {
                session.persist(product);
            } else {
                session.merge(product);
            }
            session.getTransaction().commit();
        }
    }
}
