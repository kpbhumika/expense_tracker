package com.expense_tracker.expense_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to launch the Expense Tracker application.
 */
@SpringBootApplication
public class ExpenseTrackerApplication {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);
    }

}
