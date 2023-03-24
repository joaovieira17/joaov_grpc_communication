package com.ingredient.services;

import com.ingredient.model.Ingredient;

import java.util.List;
import java.util.UUID;

public interface IngredientService {

    //gets the List of Ingredients
    List<Ingredient> getListOfIngredients();

    //gets a specific Ingredient by its ID
    Ingredient getByIngredientId(UUID ingredientId);

    //verifies if the Ingredient exists
    boolean ingredientExistence(String name);

    //creates an Ingredient
    Ingredient createIngredient(Ingredient ingredient);

    //deletes an Ingredient
    String deleteIngredient (UUID ingredientId);
}
