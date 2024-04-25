package com.expense_tracker.expense_tracker.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents an expense.
 */
@Entity
@Table(name = "expense")
public class Expense {

    /** The unique identifier for the expense. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The date of the expense. */
    @Column(name = "expense_date")
    private Date expenseDate;

    /** The description of the expense. */
    private String description;

    /** The amount of the expense. */
    private double amount;

    /** The category associated with the expense. */
    @ManyToOne
    private Category category;

    /** The user who made the expense. */
    @ManyToOne
    private User user;

    /** Default constructor. */
    public Expense() {
    }

    /**
     * Constructor to initialize Expense with id, expenseDate, description, and amount.
     *
     * @param id           The unique identifier for the expense.
     * @param expenseDate  The date of the expense.
     * @param description  The description of the expense.
     * @param amount       The amount of the expense.
     */
    public Expense(Long id, Date expenseDate, String description, double amount) {
        this.id = id;
        this.expenseDate = expenseDate;
        this.description = description;
        this.amount = amount;
    }

    /**
     * Retrieves the unique identifier of the expense.
     *
     * @return The unique identifier of the expense.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the expense.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the date of the expense.
     *
     * @return The date of the expense.
     */
    public Date getExpenseDate() {
        return expenseDate;
    }

    /**
     * Sets the date of the expense.
     *
     * @param expenseDate The date of the expense to set.
     */
    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    /**
     * Retrieves the description of the expense.
     *
     * @return The description of the expense.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the expense.
     *
     * @param description The description of the expense to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the amount of the expense.
     *
     * @return The amount of the expense.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the expense.
     *
     * @param amount The amount of the expense to set.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Retrieves the category associated with the expense.
     *
     * @return The category associated with the expense.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category associated with the expense.
     *
     * @param category The category associated with the expense to set.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Retrieves the user who made the expense.
     *
     * @return The user who made the expense.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who made the expense.
     *
     * @param user The user who made the expense to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expenseDate=" + expenseDate +
                ", description='" + description + '\'' +
                ", amount=" + amount +
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

        Expense expense = (Expense) o;

        if (id != null ? !id.equals(expense.id) : expense.id != null)
            return false;
        if (expenseDate != null ? !expenseDate.equals(expense.expenseDate) : expense.expenseDate != null)
            return false;
        if (Double.compare(expense.amount, amount) != 0)
            return false;
        return description != null ? description.equals(expense.description) : expense.description == null;
    }
}
