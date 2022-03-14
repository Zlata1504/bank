package org.example.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loan")
    private long id;

    private Date dateOfIssue;
    private Double sum;
    private Double percent;
    private Integer creditTerm;
    private Double monthlyPayment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Integer getCreditTerm() {
        return creditTerm;
    }

    public void setCreditTerm(Integer creditTerm) {
        this.creditTerm = creditTerm;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan)) return false;
        Loan loan = (Loan) o;
        return id == loan.id && Double.compare(loan.getSum(), getSum()) == 0 && Double.compare(loan.getPercent(), getPercent()) == 0 && getCreditTerm() == loan.getCreditTerm() && Double.compare(loan.getMonthlyPayment(), getMonthlyPayment()) == 0 && getDateOfIssue().equals(loan.getDateOfIssue()) && user.equals(loan.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getDateOfIssue(), getSum(), getPercent(), getCreditTerm(), getMonthlyPayment(), user);
    }
}

