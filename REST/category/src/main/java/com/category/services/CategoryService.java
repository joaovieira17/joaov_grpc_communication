package com.category.services;

import com.category.dtos.CategoryToSend;
import com.category.model.Category;

import java.util.List;
import java.util.UUID;


public interface CategoryService {

    //gets the List of Categories
    List<Category> getListOfCategories();

    //gets a specific Category by its ID
    Category getByCategoryId(UUID categoryId);

    //gets a specific Ingredient by its public key
    CategoryToSend getByPublicKey(String publicKey);

    //verifies if the Category exists by its name
    boolean categoryExistence(String name);

    //verifies if the Category exists by its public Key
    boolean categoryExistenceByKey(String publicKey);

    //creates a Category
    Category createCategory(Category category);

    //deletes an Category
    String deleteCategory (UUID categoryId);

}
