package com.expense_tracker.expense_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense_tracker.expense_tracker.model.Expense;


public interface ExpenseRepository extends JpaRepository<Expense, Long>{

    List<Expense> findByUserId(Long userId);

}
