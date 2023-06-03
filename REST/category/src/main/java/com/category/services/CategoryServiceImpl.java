package com.category.services;

import com.category.dtos.CategoryToSend;
import com.category.model.Category;
import com.category.repositories.CategoryRepository;
import com.category.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository repository;


    @Override
    public List<Category> getListOfCategories() {
        return repository.getListOfCategories();
    }

    @Override
    public Category getByCategoryId(UUID categoryId) {
        Optional<Category> categoryOptional=repository.findById(categoryId);
        if (categoryOptional.isPresent())
            return categoryOptional.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found");
    }

    @Override
    public Category getByPublicKey(String publicKey) {
        Optional<Category> categoryOptional= Optional.ofNullable(repository.getByPublicKey(publicKey));
        if (categoryOptional.isPresent())
            return categoryOptional.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found");
    }

    @Override
    public CategoryToSend getToSend(UUID categoryId) {
        Optional<Category> categoryOptional = Optional.ofNullable(repository.getByCategoryId(categoryId));
        if (categoryOptional.isPresent()) {
            String name = categoryOptional.get().getName();
            return new CategoryToSend(200, name);
        }else {
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient Not Found");
            return new CategoryToSend(404,"Not Found");
        }
    }

    @Override
    public boolean categoryExistence(String name) {
        String name1= Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("\\p{Mn}", "");

        List<Category> listOfCategories=new ArrayList<>();
        listOfCategories.addAll(repository.getByName(name));
        listOfCategories.addAll(repository.getByName(name1));

        for (int i=0; i<listOfCategories.size();i++){
            if (StringUtils.equalsIgnoreCaseAndDiacritic(listOfCategories.get(i).getName(),name1)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean categoryExistenceByKey(String publicKey) {
        String key = publicKey.toLowerCase();

        if(repository.getByPublicKey(key)!=null){
            return true;
        }
        return false;
    }

    @Override
    public Category createCategory(Category category) {
        String publicKey = category.getPublicKey().toLowerCase();

        if(repository.getByPublicKey(publicKey)!=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"There is one category with that key");
        }

        String name1= Normalizer.normalize(category.getName(), Normalizer.Form.NFD).replaceAll("\\p{Mn}", "");

        List<Category> listOfCategories=new ArrayList<>();
        listOfCategories.addAll(repository.getByName(category.getName().toLowerCase()));
        listOfCategories.addAll(repository.getByName(name1.toLowerCase()));


        for (int i=0; i<listOfCategories.size();i++){
            if (StringUtils.equalsIgnoreCaseAndDiacritic(listOfCategories.get(i).getName(),name1)){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"There is one category with that name");
            }
        }
        category.setName(name1);
        return repository.save(category);
    }

    @Override
    public String deleteCategory(UUID categoryId) {
        Optional<Category> categoryOptional=repository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            repository.delete(categoryOptional.get());
            return "Category Deleted";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found");
        }
    }
}
