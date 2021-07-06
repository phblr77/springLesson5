package ru.geekbrains;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

//2.Создайте класс ProductDao и реализуйте в нем логику выполнения CRUD-операций над сущностью
// Product (Product findById(Long id), List<Product> findAll(), void deleteById(Long id),
// Product saveOrUpdate(Product product));
public class ProductDao {
    private SessionFactory sessionFactory;

    public ProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Product> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> products = (List<Product>) session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }
}

