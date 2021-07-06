package ru.geekbrains;
//1.Создайте сущность Product (Long id, String title, int price) и таблицу в базе данных для хранения объектов этой
// сущности;
//2.Создайте класс ProductDao и реализуйте в нем логику выполнения CRUD-операций над сущностью
// Product (Product findById(Long id), List<Product> findAll(), void deleteById(Long id),
// Product saveOrUpdate(Product product));
//*3. Вшить ProductDao в веб-проект, и показывать товары, лежащие в базе данных. Помните что в таком случае
// SessionFactory или обертку над ней надо будет делать в виде Spring бина.


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {

    private static SessionFactory factory;
    private static Product product;
    private static ProductDao productDao;

    public static void main(String[] args) {
        try {
            forcePrepareData();
            init();
            product = new Product("product 11", 100);
            productDao = new ProductDao(factory);
            System.out.println(productDao.findAll());
            System.out.println(productDao.findById(5L));
            productDao.deleteById(4L);
            System.out.println(productDao.findAll());
            productDao.saveOrUpdate(product);
            System.out.println(productDao.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }

    }

    public static void init() {
        forcePrepareData();
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void forcePrepareData() {
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("full.sql")).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public static void shutdown() {
        factory.close();
    }
}
