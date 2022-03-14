package org.example.entity;

import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table
public class DebitCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_card")
    private Long id;
    private Double balance;
    private String numberOfCard;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;
    @Size(min=3,max=3,message = "enter only three numbers")
    private Integer cvv;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
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

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
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
        if (!(o instanceof DebitCard)) return false;
        DebitCard debitCard = (DebitCard) o;
        return getId() == debitCard.getId() && Double.compare(debitCard.getBalance(), getBalance()) == 0 && getCvv() == debitCard.getCvv() && getNumberOfCard().equals(debitCard.getNumberOfCard()) && getExpirationDate().equals(debitCard.getExpirationDate()) && getUser().equals(debitCard.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBalance(), getNumberOfCard(), getExpirationDate(), getCvv(), getUser());
    }
}
