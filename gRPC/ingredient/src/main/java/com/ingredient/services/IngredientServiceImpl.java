package com.ingredient.services;

import com.ingredient.dtos.CategoryResponseDTO;
import com.ingredient.dtos.CreateIngredientDTO;
import com.ingredient.dtos.IngredientToSend;
import com.ingredient.grpcService.CategoryGrpcService;
import com.ingredient.model.Category;
import com.ingredient.model.Ingredient;
import com.ingredient.repositories.IngredientRepository;
import com.ingredient.utils.StringUtils;
import com.joao.category.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.text.Normalizer;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    private IngredientRepository repository;

    private final CategoryGrpcService categoryGrpcService;

    public IngredientServiceImpl(CategoryGrpcService categoryGrpcService) {
        this.categoryGrpcService = categoryGrpcService;
    }

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Ingredient Not Found");
    }

    @Override
    public Ingredient getByPublicKey(String publicKey) {
        Optional<Ingredient> ingredientOptional= Optional.ofNullable(repository.getByPublicKey(publicKey));
        if (ingredientOptional.isPresent())
            return ingredientOptional.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Ingredient Not Found");
    }

    @Override
    public IngredientToSend getIngredientToSend(UUID ingredientId) {
        Optional<Ingredient> ingredientOptional = Optional.ofNullable(repository.getByIngredientId(ingredientId));
        if (ingredientOptional.isPresent()) {
            String name = ingredientOptional.get().getName();
            String category = ingredientOptional.get().getCategory().getCategoryName();
            return new IngredientToSend(200, name, category);
        }else {
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient Not Found");
            return new IngredientToSend(404,"Not Found", "");
        }
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
    public boolean ingredientExistenceByKey(String publicKey) {
        String key = publicKey.toLowerCase();

        if(repository.getByPublicKey(key)!=null){
            return true;
        }
        return false;
    }

    @Override
    public Ingredient createIngredient(CreateIngredientDTO createIngredientDTO) throws IOException, InterruptedException {
        String publicKey = createIngredientDTO.getPublicKey().toLowerCase();

        if(repository.getByPublicKey(publicKey)!=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"There is one ingredient with that key");
        }

        String name1= Normalizer.normalize(createIngredientDTO.getName(), Normalizer.Form.NFD).replaceAll("\\p{Mn}", "");

        List<Ingredient> listOfIngredients=new ArrayList<>();
            listOfIngredients.addAll(repository.getByName(createIngredientDTO.getName().toLowerCase()));
            listOfIngredients.addAll(repository.getByName(name1.toLowerCase()));


        for (int i=0; i<listOfIngredients.size();i++){
            if (StringUtils.equalsIgnoreCaseAndDiacritic(listOfIngredients.get(i).getName(),name1)){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"There is one ingredient with that name");
            }
        }

        //CategoryResponseDTO categoryResponseDTO = helper.CategoryByKey(createIngredientDTO.getCategoryKey());
        CategoryResponse response = categoryGrpcService.getCategory(createIngredientDTO.getCategoryKey());
        if(response.getCode()==404){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"That category does not exist");
        }

        Ingredient ingredient= new Ingredient();
        Category category = new Category(response.getName());
        ingredient.setPublicKey(createIngredientDTO.getPublicKey());
        ingredient.setName(name1);
        ingredient.setCategory(category);
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
