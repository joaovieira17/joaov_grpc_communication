package com.ingredient.bootstrapping;

import com.ingredient.model.Category;
import com.ingredient.model.Ingredient;
import com.ingredient.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Profile("bootstrap")
public class IngredientBootstrapper implements CommandLineRunner {

    @Autowired
    private IngredientRepository repository;

    Category cereais = new Category("Cereais e derivados, Tuberculos");
    Category horticolas = new Category("Horticolas");
    Category fruta = new Category("Fruta");
    Category refri = new Category("Refrigerantes");
    Category carne = new Category("Carnes e ovos");
    Category molhos = new Category("Molhos");
    Category lati = new Category("Lacticinios");

    @Override
    public void run(String... args) throws Exception {

        if (repository.getByIngredientId(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120001"))==null) {
            Ingredient p1 = new Ingredient(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120001"),"tomat","Tomate",fruta );
            repository.save(p1);
        }

        if (repository.getByIngredientId(UUID.fromString("d728f552-c747-11ed-afa1-0242ac120001"))==null) {
            Ingredient p2 = new Ingredient(UUID.fromString("d728f552-c747-11ed-afa1-0242ac120001"),"alfac","Alface",horticolas);
            repository.save(p2);
        }

        if (repository.getByIngredientId(UUID.fromString("d728f7e6-c747-11ed-afa1-0242ac120001"))==null) {
            Ingredient p3 = new Ingredient(UUID.fromString("d728f7e6-c747-11ed-afa1-0242ac120001"),"cebol","Cebola",horticolas);
            repository.save(p3);
        }

        if (repository.getByIngredientId(UUID.fromString("d728fa02-c747-11ed-afa1-0242ac120001"))==null) {
            Ingredient p4 = new Ingredient(UUID.fromString("d728fa02-c747-11ed-afa1-0242ac120001"),"chour","Chouriço",carne);
            repository.save(p4);
        }

        if (repository.getByIngredientId(UUID.fromString("d72901be-c747-11ed-afa1-0242ac120001"))==null) {
            Ingredient p5 = new Ingredient(UUID.fromString("d72901be-c747-11ed-afa1-0242ac120001"),"salsi","Salsicha",carne);
            repository.save(p5);
        }

        if (repository.getByIngredientId(UUID.fromString("d7290402-c747-11ed-afa1-0242ac120001"))==null) {
            Ingredient p6 = new Ingredient(UUID.fromString("d7290402-c747-11ed-afa1-0242ac120001"),"cogum","Cogumelos",horticolas);
            repository.save(p6);
        }

        if (repository.getByIngredientId(UUID.fromString("d729059c-c747-11ed-afa1-0242ac120001"))==null) {
            Ingredient p7 = new Ingredient(UUID.fromString("d729059c-c747-11ed-afa1-0242ac120001"),"carpo","Carne de porco",carne);
            repository.save(p7);
        }

        if (repository.getByIngredientId(UUID.fromString("d7290718-c747-11ed-afa1-0242ac120001"))==null) {
            Ingredient p8 = new Ingredient(UUID.fromString("d7290718-c747-11ed-afa1-0242ac120001"),"fiamb","Fiambre",carne);
            repository.save(p8);
        }

        if (repository.getByIngredientId(UUID.fromString("d7290830-c747-11ed-afa1-0242ac120001"))==null) {
            Ingredient p9 = new Ingredient(UUID.fromString("d7290830-c747-11ed-afa1-0242ac120001"),"maion","Maionese",molhos);
            repository.save(p9);
        }

        if (repository.getByIngredientId(UUID.fromString("d7290a9c-c747-11ed-afa1-0242ac120001"))==null) {
            Ingredient p10 = new Ingredient(UUID.fromString("d7290a9c-c747-11ed-afa1-0242ac120001"),"ketch","Ketchup",molhos);
            repository.save(p10);
        }

        if (repository.getByIngredientId(UUID.fromString("d7290c36-c747-11ed-afa1-0242ac120001"))==null) {
            Ingredient p11 = new Ingredient(UUID.fromString("d7290c36-c747-11ed-afa1-0242ac120001"),"pican","Picante",molhos);
            repository.save(p11);
        }

        if (repository.getByIngredientId(UUID.fromString("d729478c-c747-11ed-afa1-0242ac120001"))==null) {
            Ingredient p12 = new Ingredient(UUID.fromString("d729478c-c747-11ed-afa1-0242ac120001"),"queij","Queijo",lati);
            repository.save(p12);
        }

        if (repository.getByIngredientId(UUID.fromString("d729478c-c747-11ed-afa1-0242ac120000"))==null) {
            Ingredient p13 = new Ingredient(UUID.fromString("d729478c-c747-11ed-afa1-0242ac120000"),"pao","Pao",cereais);
            repository.save(p13);
        }


    }

}
