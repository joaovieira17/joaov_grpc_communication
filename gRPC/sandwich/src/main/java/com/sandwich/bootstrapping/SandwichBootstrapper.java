package com.sandwich.bootstrapping;

import com.sandwich.model.Ingredient;
import com.sandwich.model.Sandwich;
import com.sandwich.repositories.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Profile("bootstrap")
public class SandwichBootstrapper implements CommandLineRunner {

    @Autowired
    private SandwichRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Ingredient tomate=new Ingredient("tomate");
        Ingredient alface=new Ingredient("alface");
        Ingredient cogumelos=new Ingredient("cogumelos");
        Ingredient salsichas=new Ingredient("salsichas");
        Ingredient bacon= new Ingredient("bacon");
        Ingredient fiambre=new Ingredient("fiambre");

        List<Ingredient> list1 = new ArrayList<>();
        list1.add(tomate);
        list1.add(alface);
        list1.add(fiambre);

        List<Ingredient> list2 = new ArrayList<>();
        list2.add(cogumelos);
        list2.add(salsichas);
        list2.add(bacon);

        if (repository.getBySandwichId(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"))==null) {
            Sandwich p1 = new Sandwich(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"),"Baguette1","boa", list1 );
            repository.save(p1);
        }

        if (repository.getBySandwichId(UUID.fromString("d728f552-c747-11ed-afa1-0242ac120002"))==null) {
            Sandwich p2 = new Sandwich(UUID.fromString("d728f552-c747-11ed-afa1-0242ac120002"),"Baguette2","top",list1);
            repository.save(p2);
        }

        if (repository.getBySandwichId(UUID.fromString("d728f7e6-c747-11ed-afa1-0242ac120002"))==null) {
            Sandwich p3 = new Sandwich(UUID.fromString("d728f7e6-c747-11ed-afa1-0242ac120002"),"Baguette3","fixe",list1);
            repository.save(p3);
        }

        if (repository.getBySandwichId(UUID.fromString("d728fa02-c747-11ed-afa1-0242ac120002"))==null) {
            Sandwich p4 = new Sandwich(UUID.fromString("d728fa02-c747-11ed-afa1-0242ac120002"),"Baguette4","ao contrário",list1);
            repository.save(p4);
        }

        if (repository.getBySandwichId(UUID.fromString("d72901be-c747-11ed-afa1-0242ac120002"))==null) {
            Sandwich p5 = new Sandwich(UUID.fromString("d72901be-c747-11ed-afa1-0242ac120002"),"Baguette5","Em formato de coração",list1);
            repository.save(p5);
        }

        if (repository.getBySandwichId(UUID.fromString("d7290402-c747-11ed-afa1-0242ac120002"))==null) {
            Sandwich p6 = new Sandwich(UUID.fromString("d7290402-c747-11ed-afa1-0242ac120002"),"Baguette6","verde",list1);
            repository.save(p6);
        }

        if (repository.getBySandwichId(UUID.fromString("d729059c-c747-11ed-afa1-0242ac120002"))==null) {
            Sandwich p7 = new Sandwich(UUID.fromString("d729059c-c747-11ed-afa1-0242ac120002"),"Baguette7","Em muita quantidade",list1);
            repository.save(p7);
        }

        if (repository.getBySandwichId(UUID.fromString("d7290718-c747-11ed-afa1-0242ac120002"))==null) {
            Sandwich p8 = new Sandwich(UUID.fromString("d7290718-c747-11ed-afa1-0242ac120002"),"Baguette8","pequena",list2);
            repository.save(p8);
        }

        if (repository.getBySandwichId(UUID.fromString("d7290830-c747-11ed-afa1-0242ac120002"))==null) {
            Sandwich p9 = new Sandwich(UUID.fromString("d7290830-c747-11ed-afa1-0242ac120002"),"Baguette9","grande",list2);
            repository.save(p9);
        }

        if (repository.getBySandwichId(UUID.fromString("d7290a9c-c747-11ed-afa1-0242ac120002"))==null) {
            Sandwich p10 = new Sandwich(UUID.fromString("d7290a9c-c747-11ed-afa1-0242ac120002"),"Baguette10","yay",list2);
            repository.save(p10);
        }

        if (repository.getBySandwichId(UUID.fromString("d7290c36-c747-11ed-afa1-0242ac120002"))==null) {
            Sandwich p11 = new Sandwich(UUID.fromString("d7290c36-c747-11ed-afa1-0242ac120002"),"Baguette11","maravilha",list2);
            repository.save(p11);
        }

        if (repository.getBySandwichId(UUID.fromString("d729478c-c747-11ed-afa1-0242ac120002"))==null) {
            Sandwich p12 = new Sandwich(UUID.fromString("d729478c-c747-11ed-afa1-0242ac120002"),"Baguette12","mais do mesmo",list2);
            repository.save(p12);
        }


    }

}
