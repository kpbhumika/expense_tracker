package com.expense_tracker.expense_tracker.model;

import java.time.Instant;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="expense")
public class Expense {

    @Id
    private Long id;
    private Instant expenseDate;
    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    public Expense() {
    }

    public Expense(Long id, Instant expenseDate, String description) {
        this.id = id;
        this.expenseDate = expenseDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Instant expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense = (Expense) o;

        if (id != null ? !id.equals(expense.id) : expense.id != null) return false;
        if (expenseDate != null ? !expenseDate.equals(expense.expenseDate) : expense.expenseDate != null)
            return false;
        return description != null ? description.equals(expense.description) : expense.description == null;
    }
}
