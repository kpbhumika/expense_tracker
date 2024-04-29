package com.expense_tracker.expense_tracker;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.expense_tracker.expense_tracker.model.Category;
import com.expense_tracker.expense_tracker.model.Expense;
import com.expense_tracker.expense_tracker.repository.ExpenseRepository;
import com.expense_tracker.expense_tracker.service.ExpenseService;

@SpringBootTest
public class ExpenseServiceTest {


    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    private List<Expense> expenseList;
    private Category category;

    @BeforeEach
    public void setUp() {
        expenseList = new ArrayList<>();
        category = new Category(1L, "Food");

        Expense expense1 = new Expense();
        expense1.setId(1L);
        expense1.setCategory(category);
        expense1.setDescription("Lunch");
        expense1.setAmount(20.0);

        Expense expense2 = new Expense();
        expense2.setId(2L);
        expense2.setCategory(category);
        expense2.setDescription("Dinner");
        expense2.setAmount(30.0);

        expenseList.add(expense1);
        expenseList.add(expense2);
    }

    @Test
    public void testGetExpensesByUserId() {
        Long userId = 1L;
        when(expenseRepository.findByUserId(userId)).thenReturn(expenseList);

        List<Expense> result = expenseService.getExpensesByUserId(userId);

        assertEquals(expenseList.size(), result.size());
        assertEquals(expenseList, result);
    }

    @Test
    public void testGenerateExpenseReport() throws IOException {

        // Mocking saveStringToFile method to avoid actual file operations
        ExpenseService spyExpenseService = spy(expenseService);
        doNothing().when(spyExpenseService).saveStringToFile(anyString(), anyString());

        spyExpenseService.generateExpenseReport(1L, expenseList, 60.0);

        // Asserting if the method calls saveStringToFile with the correct content and file path
        verify(spyExpenseService).saveStringToFile(contains("Total amount spent : $60.0"), eq( "./report.txt"));
    }
}
