package com.expense_tracker.expense_tracker.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense_tracker.expense_tracker.model.Category;
import com.expense_tracker.expense_tracker.repository.CategoryRepository;

/**
 * Controller class for handling HTTP requests related to categories.
 */
@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryRepository categoryRepository;

    /**
     * Constructor for CategoryController.
     *
     * @param categoryRepository The repository for category data.
     */
    public CategoryController(CategoryRepository categoryRepository) {
        super();
        this.categoryRepository = categoryRepository;
    }

    /**
     * Endpoint to retrieve all categories.
     *
     * @return A collection of categories.
     */
    @GetMapping("/categories")
    Collection<Category> categories() {
        return categoryRepository.findAll();
    }

    /**
     * Endpoint to retrieve a specific category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return ResponseEntity containing the category if found, or HttpStatus.NOT_FOUND if not found.
     */
    @GetMapping("/category/{id}")
    ResponseEntity<?> getCategory(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint to create a new category.
     *
     * @param category The category object to be created.
     * @return ResponseEntity containing the created category and HTTP status CREATED.
     * @throws URISyntaxException If there is an issue with creating the URI.
     */
    @PostMapping("/category")
    ResponseEntity<Category> createCategory(@RequestBody Category category) throws URISyntaxException {
        Category result = categoryRepository.save(category);
        return ResponseEntity.created(new URI("/api/category" + result.getId())).body(result);
    }

    /**
     * Endpoint to update an existing category.
     *
     * @param category The updated category object.
     * @return ResponseEntity containing the updated category.
     */
    @PutMapping("/category/{id}")
    ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        Category result = categoryRepository.save(category);
        return ResponseEntity.ok().body(result);
    }

    /**
     * Endpoint to delete a category by its ID.
     *
     * @param id The ID of the category to delete.
     * @return ResponseEntity with HTTP status OK if deletion is successful.
     */
    @DeleteMapping("/category/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
