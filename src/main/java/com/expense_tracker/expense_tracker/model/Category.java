package com.expense_tracker.expense_tracker.model;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a category for expenses.
 */
@Entity
@Table(name = "category")
public class Category {

    /** The unique identifier for the category. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The name of the category. */
    private String name;

    /** The set of expenses associated with this category. */
    @OneToMany
    private Set<Expense> expenses;

    /** Default constructor. */
    public Category() {
    }

    /**
     * Constructor to initialize Category with id and name.
     *
     * @param id   The unique identifier for the category.
     * @param name The name of the category.
     */
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retrieves the unique identifier of the category.
     *
     * @return The unique identifier of the category.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the category.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the category.
     *
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
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

        Category category = (Category) o;

        if (id != null ? !id.equals(category.id) : category.id != null)
            return false;
        return name != null ? name.equals(category.name) : category.name == null;
    }
}
