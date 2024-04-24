package com.expense_tracker.expense_tracker.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
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

import org.springframework.web.bind.annotation.RequestParam;

// import service.FileImportService;

@RestController
@RequestMapping("/api")
public class ExpenseController {

	private final ExpenseRepository expenseRepository;
	private final ExpenseService expenseService;

	public ExpenseController(ExpenseRepository expenseRepository, ExpenseService expenseService) { // dependency injection
		super();
		this.expenseRepository = expenseRepository;
		this.expenseService = expenseService;
	}

	@GetMapping("/expenses")
	List<Expense> getExpenses() {
		return expenseRepository.findAll();
	}

	@DeleteMapping("/expenses/{id}")
	ResponseEntity<?> deleteExpense(@PathVariable Long id) {
		expenseRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/expenses")
	ResponseEntity<Expense> createExpense(@RequestBody Expense expense) throws URISyntaxException {
		LocalDate currentDate = LocalDate.now();
		Date currentDateAsSqlDate = Date.valueOf(currentDate);
		expense.setExpenseDate(currentDateAsSqlDate);

		Expense result = expenseRepository.save(expense);
		return ResponseEntity.created(new URI("/api/expenses" + result.getId())).body(result);
	}

	@GetMapping("/expenses/{userId}")
	public ResponseEntity<?> getExpenseReport(@PathVariable Long userId)  {
		// Call the ExpenseService to retrieve expenses for the specified user ID
		List<Expense> expenses = expenseService.getExpensesByUserId(userId);

		double totalExpenseAmount = 0;
		for (int i=0;i<expenses.size();i++){
			Expense expense = expenses.get(i);
			double amount = expense.getAmount();
			totalExpenseAmount +=amount;
		}

		//TODO: add actual userId
		Long dummyUserId = 1L;
		try {
			expenseService.generateExpenseReport(dummyUserId, totalExpenseAmount);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!expenses.isEmpty()) {
			return ResponseEntity.ok(totalExpenseAmount); // Return the list of expenses if found
		} else {
			return ResponseEntity.notFound().build(); // Return 404 Not Found if expenses not found
		}
	}

	// @PostMapping("/import")
	// public ResponseEntity<String> importExpenses(@RequestParam("file")
	// MultipartFile file) throws IOException {
	// fileImportService.processFile(file);
	// return ResponseEntity.ok("Expenses imported successfully!");
	// }

}