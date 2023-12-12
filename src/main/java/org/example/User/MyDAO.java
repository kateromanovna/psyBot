package org.example.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MyDAO {

    private final SessionFactory sessionFactory;

    // Констуктор по умолчанию, что такое сессион фактори читай в MAin
    public MyDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Сохранить обьект класса в бд
    public void saveOrUpdate(User entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        }
    }

    // Получить обьект класса из бд
    public User findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }


}