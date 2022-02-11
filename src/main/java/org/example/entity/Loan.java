package org.example.entity;

import java.util.Date;
import java.util.Objects;

public class Loan {
    private Date dateOfIssue;
    private double sum;
    private double percent;
    private int creditTerm;
    private double monthlyPayment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan)) return false;
        Loan loan = (Loan) o;
        return Double.compare(loan.getSum(), getSum()) == 0 && Double.compare(loan.getPercent(), getPercent()) == 0 && getCreditTerm() == loan.getCreditTerm() && Double.compare(loan.getMonthlyPayment(), getMonthlyPayment()) == 0 && Objects.equals(getDateOfIssue(), loan.getDateOfIssue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateOfIssue(), getSum(), getPercent(), getCreditTerm(), getMonthlyPayment());
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getPercent() {
        return percent;
    }


    public int getCreditTerm() {
        return creditTerm;
    }

    public void setCreditTerm(int creditTerm) {
        this.creditTerm = creditTerm;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
