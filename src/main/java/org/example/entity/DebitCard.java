package org.example.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

public class DebitCard {
    private double balance;
    private String numberOfCard;
    private Date expirationDate;
    @NotEmpty(message = "string should not be empty")
    @Size(min=3,max=3,message = "enter only three numbers")
    private int cvv;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DebitCard)) return false;
        DebitCard debitCard = (DebitCard) o;
        return Double.compare(debitCard.getBalance(), getBalance()) == 0 && getCvv() == debitCard.getCvv() && Objects.equals(getNumberOfCard(), debitCard.getNumberOfCard()) && Objects.equals(getExpirationDate(), debitCard.getExpirationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBalance(), getNumberOfCard(), getExpirationDate(), getCvv());
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNumberOfCard() {
        return numberOfCard;
    }

    public void setNumberOfCard(String numberOfCard) {
        this.numberOfCard = numberOfCard;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
