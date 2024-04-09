package com.expense_tracker.expense_tracker.model;

import java.time.Instant;

public class Expense {

    private Long id;
	private Instant expensedate;
	private String description;
	private String location;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Instant getExpensedate() {
        return expensedate;
    }
    public void setExpensedate(Instant expensedate) {
        this.expensedate = expensedate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }


}
