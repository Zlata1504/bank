package org.example.dao;

import org.example.entity.DebitCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class DebitCardDao {
    private final SessionFactory sessionFactory;

    public DebitCardDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addCard(DebitCard debitCard){
        Session session = sessionFactory.openSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(debitCard);
        transaction1.commit();
        session.close();
    }
}