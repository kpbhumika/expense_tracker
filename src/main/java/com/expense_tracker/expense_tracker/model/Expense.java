package com.expense_tracker.expense_tracker.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_date")
    private Date expenseDate;

    private String description;
    private double amount;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    public Expense() {
    }

    public Expense(Long id, Date expenseDate, String description, double amount) {
        this.id = id;
        this.expenseDate = expenseDate;
        this.description = description;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expenseDate=" + expenseDate +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }

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
