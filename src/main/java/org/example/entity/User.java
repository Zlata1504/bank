package org.example.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1)
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_users")
    private Long id;

    @NotEmpty(message = "email is empty")
    @Email(message = "email should be valid")
    private String email;

    private Date registrationDate;

    @NotEmpty
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Loan> usersLoans;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DebitCard> usersCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Loan> getUsersLoans() {
        return usersLoans;
    }

    public void setUsersLoans(List<Loan> usersLoans) {
        this.usersLoans = usersLoans;
    }

    public List<DebitCard> getUsersCard() {
        return usersCard;
    }

    public void setUsersCard(List<DebitCard> usersCard) {
        this.usersCard = usersCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && getFirstName().equals(user.getFirstName()) && getLastName().equals(user.getLastName()) && getEmail().equals(user.getEmail()) && getRegistrationDate().equals(user.getRegistrationDate()) && getPassword().equals(user.getPassword()) && usersCard.equals(user.usersCard)&&usersLoans.equals(user.usersLoans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), id, getEmail(), getRegistrationDate(), getPassword(), usersCard,usersLoans);
    }
}
