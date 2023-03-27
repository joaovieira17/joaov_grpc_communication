package com.sandwich.services;

import com.sandwich.grpcService.IngredientGrpcService;
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

    private final IngredientGrpcService ingredientGrpcService;

    public SandwichServiceImpl(IngredientGrpcService ingredientGrpcService) {
        this.ingredientGrpcService = ingredientGrpcService;
    }

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
            int code = ingredientGrpcService.getIngredient(listOfIngredients.get(i).getName()).getCode();
            if(code == 404){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"That ingredient does not exist: "+listOfIngredients.get(i).getName());
            }
        }
        if(repository.getSandwichByDesignation(sandwich.getDesignation())!=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "That name is already used");
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
