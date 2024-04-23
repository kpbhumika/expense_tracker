package com.expense_tracker.expense_tracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.expense_tracker.expense_tracker.model.Category;
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Category findByName(String name);
}
