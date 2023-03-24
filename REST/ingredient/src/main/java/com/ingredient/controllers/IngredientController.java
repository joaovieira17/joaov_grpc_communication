package com.ingredient.controllers;

import com.ingredient.model.Ingredient;
import com.ingredient.services.IngredientService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//@Tag(name = "Sandwiches", description = "Endpoints for managing sandwiches")
@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService service;

    //@Operation(summary = "Get List of ingredients")
    @GetMapping("/list")
    public Iterable<Ingredient> getListOfIngredients() {
        return service.getListOfIngredients();
    }

    //@Operation(summary = "Gets a specific ingredient by its ID")
    @GetMapping("/{ingredientId}")
    public ResponseEntity<Ingredient> getBySandwichId(@PathVariable("ingredientId") final UUID ingredientId) {
        final Ingredient ingredient = service.getByIngredientId(ingredientId);
        return ResponseEntity.ok().body(ingredient);
    }

    @GetMapping("/existence/{name}")
    public ResponseEntity<Boolean> checkSandwichExistence(@PathVariable("name") final String name) {
        boolean exists = service.ingredientExistence(name.toLowerCase());
        return ResponseEntity.ok().body(exists);
    }

    //@Operation(summary = "Create a ingredient")
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Ingredient> createSandwich(@Valid @RequestBody final Ingredient ingredient) {
        final Ingredient ingredient1 = service.createIngredient(ingredient);
        return ResponseEntity.ok(ingredient1);
    }

    //@Operation(summary = "Deletes a ingredient")
    @DeleteMapping(value = "/delete/{ingredientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteSandwich(@PathVariable("ingredientId")final UUID ingredientId){
        return ResponseEntity.ok(service.deleteIngredient(ingredientId));
    }





}
