package com.ingredient.repositories;

import com.ingredient.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {

    @Query("SELECT f FROM Ingredient f WHERE f.ingredientId= :ingredientId")
    Ingredient getByIngredientId(@Param("ingredientId") UUID ingredientId);

    @Query("SELECT  f FROM Ingredient f")
    List<Ingredient> getListOfIngredients();

    @Query("SELECT f FROM Ingredient f WHERE LOWER(f.name) like %:name%")
    List<Ingredient> getByName(@Param("name") String name);

    @Query("SELECT f FROM Ingredient f WHERE LOWER(f.publicKey) like %:publicKey%")
    List<Ingredient> getByPublicKey(@Param("publicKey") String publicKey);


}
