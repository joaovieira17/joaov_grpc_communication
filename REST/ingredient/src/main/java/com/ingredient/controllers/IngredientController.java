package com.ingredient.controllers;

import com.ingredient.dtos.CreateIngredientDTO;
import com.ingredient.dtos.IngredientToSend;
import com.ingredient.model.Ingredient;
import com.ingredient.services.IngredientService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

//@Tag(name = "Ingredients", description = "Endpoints for managing ingredients")
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
    public ResponseEntity<Ingredient> getByIngredientId(@PathVariable("ingredientId") final UUID ingredientId) {
        final Ingredient ingredient = service.getByIngredientId(ingredientId);
        return ResponseEntity.ok().body(ingredient);
    }

    @GetMapping("/getByKey/{publicKey}")
    public ResponseEntity<IngredientToSend> getByPublicKey(@PathVariable("publicKey") final String publicKey) {
        final IngredientToSend ingredient = service.getByPublicKey(publicKey);
        return ResponseEntity.ok().body(ingredient);
    }

    @GetMapping("/existence/{name}")
    public ResponseEntity<Boolean> checkIngredientExistence(@PathVariable("name") final String name) {
        boolean exists = service.ingredientExistence(name.toLowerCase());
        return ResponseEntity.ok().body(exists);
    }

    @GetMapping("/existenceByKey/{publicKey}")
    public ResponseEntity<Boolean> checkIngredientExistenceByKey(@PathVariable("publicKey") final String publicKey) {
        boolean exists = service.ingredientExistenceByKey(publicKey.toLowerCase());
        return ResponseEntity.ok().body(exists);
    }

    //@Operation(summary = "Create a ingredient")
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Ingredient> createIngredient(@Valid @RequestBody final CreateIngredientDTO ingredient) throws IOException, InterruptedException {
        final Ingredient ingredient1 = service.createIngredient(ingredient);
        return ResponseEntity.ok(ingredient1);
    }

    //@Operation(summary = "Deletes an ingredient")
    @DeleteMapping(value = "/delete/{ingredientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteIngredient(@PathVariable("ingredientId")final UUID ingredientId){
        return ResponseEntity.ok(service.deleteIngredient(ingredientId));
    }





}
