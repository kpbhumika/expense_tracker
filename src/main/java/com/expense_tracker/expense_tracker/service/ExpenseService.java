package com.expense_tracker.expense_tracker.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.expense_tracker.expense_tracker.repository.ExpenseRepository;
import com.expense_tracker.expense_tracker.model.Expense;

/**
 * Service class for handling operations related to expenses.
 */
@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final String reportFolder = "/Users/bhumikakp/JAVA"; // Folder to save the generated report

    /**
     * Constructor for ExpenseService.
     *
     * @param expenseRepository The repository for expense data.
     */
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    /**
     * Retrieves expenses associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return List of expenses associated with the user.
     */
    public List<Expense> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    /**
     * Saves a string to a file.
     *
     * @param content  The content to be written to the file.
     * @param filePath The path of the file to write.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveStringToFile(String content, String filePath) throws IOException {
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(content);
        writer.close(); // Important to close the streams to release resources
    }

    /**
     * Generates an expense report for a user and saves it to a file.
     *
     * @param userId             The ID of the user.
     * @param expenses           List of expenses for the user.
     * @param totalExpenseAmount The total amount of expenses for the user.
     * @throws IOException If an I/O error occurs while saving the report.
     */
    public void generateExpenseReport(Long userId, List<Expense> expenses, double totalExpenseAmount)
            throws IOException {
        String allExpenses = "";
        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            String categoryName = expense.getCategory().getName();
            allExpenses += categoryName + "\t\t" + "$" + expense.getAmount() + "\t\t" + expense.getDescription() + "\n";
        }

        String reportString = allExpenses + "Total amount spent : " + "$" + totalExpenseAmount;
        saveStringToFile(reportString, reportFolder + "/report.txt");
    }
}
