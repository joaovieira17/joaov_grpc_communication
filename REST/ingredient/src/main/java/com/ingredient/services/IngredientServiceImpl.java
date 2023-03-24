package com.ingredient.services;

import com.ingredient.model.Ingredient;
import com.ingredient.repositories.IngredientRepository;
import com.ingredient.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    private IngredientRepository repository;

    @Override
    public List<Ingredient> getListOfIngredients() {
        return repository.getListOfIngredients();
    }

    @Override
    public Ingredient getByIngredientId(UUID ingredientId) {
        Optional<Ingredient> ingredientOptional=repository.findById(ingredientId);
        if (ingredientOptional.isPresent())
            return ingredientOptional.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sandwich Not Found");
    }

    @Override
    public boolean ingredientExistence(String name) {
        String name1= Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("\\p{Mn}", "");

        List<Ingredient> listOfIngredients=new ArrayList<>();
                listOfIngredients.addAll(repository.getByName(name));
                listOfIngredients.addAll(repository.getByName(name1));

        for (int i=0; i<listOfIngredients.size();i++){
            if (StringUtils.equalsIgnoreCaseAndDiacritic(listOfIngredients.get(i).getName(),name1)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        String name1= Normalizer.normalize(ingredient.getName(), Normalizer.Form.NFD).replaceAll("\\p{Mn}", "");

        List<Ingredient> listOfIngredients=new ArrayList<>();
            listOfIngredients.addAll(repository.getByName(ingredient.getName().toLowerCase()));
            listOfIngredients.addAll(repository.getByName(name1.toLowerCase()));


        for (int i=0; i<listOfIngredients.size();i++){
            if (StringUtils.equalsIgnoreCaseAndDiacritic(listOfIngredients.get(i).getName(),name1)){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"There is one ingredient with that name");
            }
        }
        ingredient.setName(name1);
        return repository.save(ingredient);
    }

    @Override
    public String deleteIngredient(UUID ingredientId) {
        Optional<Ingredient> ingredientOptional=repository.findById(ingredientId);
        if (ingredientOptional.isPresent()) {
            repository.delete(ingredientOptional.get());
            return "Ingredient Deleted";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Ingredient Not Found");
        }
    }
}
