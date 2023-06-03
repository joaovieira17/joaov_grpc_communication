package com.sandwich.services;

import com.sandwich.dtos.CreateSandwichDTO;
import com.sandwich.dtos.IngredientKeyDTO;
import com.sandwich.dtos.IngredientResponseDTO;
import com.sandwich.model.Catalog;
import com.sandwich.model.Ingredient;
import com.sandwich.model.Sandwich;
import com.sandwich.repositories.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

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
    public Sandwich getByPublicKey(String publicKey) {
        Optional<Sandwich> sandwichOptional = Optional.ofNullable(repository.getSandwichByPublicKey(publicKey));
        if(sandwichOptional.isPresent()){
            return sandwichOptional.get();
        }
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
    public boolean sandwichExistenceByKey(String publicKey) {
        Optional<Sandwich> sandwichOptional = Optional.ofNullable(repository.getSandwichByPublicKey(publicKey));

        boolean isPresent= sandwichOptional.isPresent();
        return isPresent;
    }

    @Override
    public Sandwich createSandwich(CreateSandwichDTO sandwichDTO) throws IOException, InterruptedException {
        List<IngredientKeyDTO> listOfIngredientsDTO1= sandwichDTO.getListOfIngredients();
        Set<IngredientKeyDTO> setSemDuplicados = new LinkedHashSet<>(listOfIngredientsDTO1);
        List<IngredientKeyDTO> listOfIngredientsDTO = new ArrayList<>(setSemDuplicados);

        List<Ingredient> listOfIngredients = new ArrayList<>();
        for(int i=0; i<listOfIngredientsDTO.size();i++){
            IngredientResponseDTO ingredientResponseDTO = helper.IngredientByKey(listOfIngredientsDTO.get(i).getPrivateKey());
            if(ingredientResponseDTO.getCode()==404){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"That ingredient does not exist: "+listOfIngredientsDTO.get(i).getPrivateKey());
            }

            Ingredient ingredient = new Ingredient(ingredientResponseDTO.getName(), ingredientResponseDTO.getCategory());
            listOfIngredients.add(ingredient);
        }
        if(repository.getSandwichByDesignation(sandwichDTO.getDesignation())!=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "That name is already used");
        }
        Sandwich sandwich = new Sandwich(sandwichDTO.getSandwichId(),sandwichDTO.getPublicKey(),sandwichDTO.getDesignation(),sandwichDTO.getDescription(),listOfIngredients);
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
