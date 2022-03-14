package org.example.dao;

import org.example.entity.Loan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class LoanDao {
    private final SessionFactory sessionFactory;

    public LoanDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Loan loan) {
        Session session = sessionFactory.openSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(loan);
        transaction1.commit();
        session.close();
    }
}