package com.expense_tracker.expense_tracker.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.expense_tracker.expense_tracker.repository.ExpenseRepository;
import com.expense_tracker.expense_tracker.model.Expense;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final String reportFolder = "/Users/bhumikakp/JAVA";
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId);
    }
    private static void saveStringToFile(String content, String filePath) throws IOException {
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(content);
        writer.close(); // Important to close the streams to release resources
      }


    public void generateExpenseReport(Long userId, List<Expense> expenses, double totalExpenseAmount) throws IOException {
        String allExpenses = "";
        for (int i = 0; i < expenses.size(); i++) {
			Expense expense = expenses.get(i);
            String categoryName = expense.getCategory().getName();
            allExpenses += categoryName + "\t\t"  + "$" + expense.getAmount() + "\t\t"  + expense.getDescription() + "\n";
		}

        String reportString = allExpenses + "Total amount spent : " + "$" + totalExpenseAmount;
        saveStringToFile(reportString,reportFolder+"/report.txt");
    }
}

