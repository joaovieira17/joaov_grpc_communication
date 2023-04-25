package com.ingredient.services;

import com.ingredient.dtos.IngredientToSend;
import com.ingredient.model.Ingredient;

import java.util.List;
import java.util.UUID;

public interface IngredientService {

    //gets the List of Ingredients
    List<Ingredient> getListOfIngredients();

    //gets a specific Ingredient by its ID
    Ingredient getByIngredientId(UUID ingredientId);

    //gets a specific Ingredient by its public key
    IngredientToSend getByPublicKey(String publicKey);

    //verifies if the Ingredient exists by its name
    boolean ingredientExistence(String name);

    //verifies if the Ingredient exists by its public Key
    boolean ingredientExistenceByKey(String publicKey);

    //creates an Ingredient
    Ingredient createIngredient(Ingredient ingredient);

    //deletes an Ingredient
    String deleteIngredient (UUID ingredientId);
}
