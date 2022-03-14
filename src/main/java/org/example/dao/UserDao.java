package org.example.dao;

import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
public class UserDao {
    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(user);
        transaction1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction1 = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET firstName =:firstNameParam, lastName =:lastNameParam, password =:passwordParam WHERE email =:emailParam");
        query.setParameter("emailParam", user.getEmail());
        query.setParameter("firstNameParam", user.getFirstName());
        query.setParameter("lastNameParam", user.getLastName());
        query.setParameter("passwordParam", user.getPassword());
        query.executeUpdate();
        transaction1.commit();
        session.close();
    }

    public User findByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction1 = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE email =:paramEmail AND password=:paramPassword");
        query.setParameter("paramEmail",email);
        query.setParameter("paramPassword",password);
        List<User> users = (List<User>) query.getResultList();
        transaction1.commit();
        session.close();
        return users.size() > 0 ? users.get(0) : null;
    }
}
