package com.sandwich.services;

import com.sandwich.model.Catalog;
import com.sandwich.model.Ingredient;
import com.sandwich.model.Sandwich;
import com.sandwich.repositories.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SandwichServiceImpl implements SandwichService{

    @Autowired
    private SandwichRepository repository;

    private HttpRequestHelper helper = new HttpRequestHelper();

    @Override
    public List<Catalog> getCatalog() {
        return repository.getCatalog();
    }

    @Override
    public Sandwich getBySandwichId(UUID sandwichId) {
        Optional<Sandwich> sandwichOptional=repository.findById(sandwichId);
        if (sandwichOptional.isPresent())
            return sandwichOptional.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sandwich Not Found");
    }

    @Override
    public boolean sandwichExistence(UUID sandwichId) {
        Optional<Sandwich> sandwichOptional=repository.findById(sandwichId);
        boolean isPresent=sandwichOptional.isPresent();
        return isPresent;
    }

    @Override
    public Sandwich createSandwich(Sandwich sandwich) throws IOException, InterruptedException {
        List<Ingredient> listOfIngredients= sandwich.getListOfIngredients();

        for(int i=0; i<listOfIngredients.size();i++){
            boolean isIngredient = helper.doesIngredientExist(listOfIngredients.get(i).getName());
            if(!isIngredient){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"That ingredient does not exist: "+listOfIngredients.get(i).getName());
            }
        }
        return repository.save(sandwich);
    }

    @Override
    public String deleteSandwich(UUID sandwichId) {
        Optional<Sandwich> sandwichOptional=repository.findById(sandwichId);
        if (sandwichOptional.isPresent()) {
            repository.delete(sandwichOptional.get());
            return "Sandwich Deleted";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sandwich Not Found");
        }
    }
}
