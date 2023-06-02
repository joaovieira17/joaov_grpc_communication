package com.category.controllers;

import com.category.dtos.CategoryToSend;
import com.category.model.Category;
import com.category.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    //@Operation(summary = "Get List of ingredients")
    @GetMapping("/list")
    public Iterable<Category> getListOfCategories() {
        return service.getListOfCategories();
    }

    //@Operation(summary = "Gets a specific category by its ID")
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getByIngredientId(@PathVariable("categoryId") final UUID categoryId) {
        final Category category = service.getByCategoryId(categoryId);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/getByKey/{publicKey}")
    public ResponseEntity<CategoryToSend> getByPublicKey(@PathVariable("publicKey") final String publicKey) {
        final CategoryToSend ingredient = service.getByPublicKey(publicKey);
        return ResponseEntity.ok().body(ingredient);
    }

    @GetMapping("/existence/{name}")
    public ResponseEntity<Boolean> checkCategoryExistence(@PathVariable("name") final String name) {
        boolean exists = service.categoryExistence(name.toLowerCase());
        return ResponseEntity.ok().body(exists);
    }

    @GetMapping("/existenceByKey/{publicKey}")
    public ResponseEntity<Boolean> checkCategoryExistenceByKey(@PathVariable("publicKey") final String publicKey) {
        boolean exists = service.categoryExistenceByKey(publicKey.toLowerCase());
        return ResponseEntity.ok().body(exists);
    }

    //@Operation(summary = "Create a category")
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> createCategory(@Valid @RequestBody final Category category) {
        final Category category1 = service.createCategory(category);
        return ResponseEntity.ok(category1);
    }

    //@Operation(summary = "Deletes an ingredient")
    @DeleteMapping(value = "/delete/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId")final UUID categoryId){
        return ResponseEntity.ok(service.deleteCategory(categoryId));
    }
}
