package com.expense_tracker.expense_tracker.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense_tracker.expense_tracker.model.Expense;
import com.expense_tracker.expense_tracker.repository.ExpenseRepository;
import com.expense_tracker.expense_tracker.service.ExpenseService;

/**
 * Controller class for handling HTTP requests related to expenses.
 */
@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseRepository expenseRepository;
    private final ExpenseService expenseService;

    /**
     * Constructor for ExpenseController.
     *
     * @param expenseRepository The repository for expense data.
     * @param expenseService    The service for expense-related operations.
     */
    public ExpenseController(ExpenseRepository expenseRepository, ExpenseService expenseService) {
		super();
        this.expenseRepository = expenseRepository;
        this.expenseService = expenseService;
    }

    /**
     * Endpoint to retrieve all expenses.
     *
     * @return List of all expenses.
     */
    @GetMapping("/expenses")
    List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    /**
     * Endpoint to delete an expense by its ID.
     *
     * @param id The ID of the expense to delete.
     * @return ResponseEntity with HTTP status OK if deletion is successful.
     */
    @DeleteMapping("/expenses/{id}")
    ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to create a new expense.
     *
     * @param expense The expense object to be created.
     * @return ResponseEntity containing the created expense and HTTP status CREATED.
     * @throws URISyntaxException If there is an issue with creating the URI.
     */
    @PostMapping("/expenses")
    ResponseEntity<Expense> createExpense(@RequestBody Expense expense) throws URISyntaxException {
        LocalDate currentDate = LocalDate.now();
        Date currentDateAsSqlDate = Date.valueOf(currentDate);
        expense.setExpenseDate(currentDateAsSqlDate);

        Expense result = expenseRepository.save(expense);
        return ResponseEntity.created(new URI("/api/expenses" + result.getId())).body(result);
    }

    /**
     * Endpoint to get an expense report for a specific user.
     *
     * @param userId The ID of the user for which the expense report is requested.
     * @return ResponseEntity containing the total expense amount for the user.
     */
    @GetMapping("/expenses/{userId}")
    public ResponseEntity<?> getExpenseReport(@PathVariable Long userId) {
        // Call the ExpenseService to retrieve expenses for the specified user ID
        List<Expense> expenses = expenseService.getExpensesByUserId(userId);

        double totalExpenseAmount = 0;
		for (int i = 0; i < expenses.size(); i++) {
			Expense expense = expenses.get(i);
			double amount = expense.getAmount();
			totalExpenseAmount += amount;
        }

        try {
            expenseService.generateExpenseReport(userId, expenses, totalExpenseAmount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!expenses.isEmpty()) {
            return ResponseEntity.ok(totalExpenseAmount); // Return the total expense amount if expenses found
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if expenses not found
        }
    }
}
