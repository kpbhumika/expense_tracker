package com.expense_tracker.expense_tracker.model;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a user.
 */
@Entity
@Table(name = "`user`") // Using backticks for table name to avoid conflict with reserved keywords
public class User {

    /** The unique identifier for the user. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The name of the user. */
    private String name;

    /** The email of the user. */
    private String email;

    /** The set of expenses associated with this user. */
    @OneToMany
    private Set<Expense> expense;

    /** Default constructor. */
    public User() {
    }

    /**
     * Constructor to initialize User with id, name, and email.
     *
     * @param id    The unique identifier for the user.
     * @param name  The name of the user.
     * @param email The email of the user.
     */
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Retrieves the unique identifier of the user.
     *
     * @return The unique identifier of the user.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null)
            return false;
        if (name != null ? !name.equals(user.name) : user.name != null)
            return false;
        return email != null ? email.equals(user.email) : user.email == null;
    }
}
