package com.category.bootstrapping;

import com.category.model.Category;
import com.category.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Profile("bootstrap")
public class CategoryBootstrapper implements CommandLineRunner {

    @Autowired
    private CategoryRepository repository;

    @Override
    public void run(String... args) throws Exception {

        if (repository.getByCategoryId(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"))==null) {
            Category p1 = new Category(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"),"certu","Cereais e derivados, Tuberculos" );
            repository.save(p1);
        }

        if (repository.getByCategoryId(UUID.fromString("d828f552-c747-11ed-afa1-0242ac120001"))==null) {
            Category p2 = new Category(UUID.fromString("d828f552-c747-11ed-afa1-0242ac120001"),"horti","Horticolas");
            repository.save(p2);
        }

        if (repository.getByCategoryId(UUID.fromString("d828f7e6-c747-11ed-afa1-0242ac120001"))==null) {
            Category p3 = new Category(UUID.fromString("d828f7e6-c747-11ed-afa1-0242ac120001"),"fruta","Fruta");
            repository.save(p3);
        }

        if (repository.getByCategoryId(UUID.fromString("d828fa02-c747-11ed-afa1-0242ac120001"))==null) {
            Category p4 = new Category(UUID.fromString("d828fa02-c747-11ed-afa1-0242ac120001"),"gordo","Gorduras e oleos");
            repository.save(p4);
        }

        if (repository.getByCategoryId(UUID.fromString("d82901be-c747-11ed-afa1-0242ac120001"))==null) {
            Category p5 = new Category(UUID.fromString("d82901be-c747-11ed-afa1-0242ac120001"),"lacti","Lacticinios");
            repository.save(p5);
        }

        if (repository.getByCategoryId(UUID.fromString("d8290402-c747-11ed-afa1-0242ac120001"))==null) {
            Category p6 = new Category(UUID.fromString("d8290402-c747-11ed-afa1-0242ac120001"),"carne","Carnes e ovos");
            repository.save(p6);
        }

        if (repository.getByCategoryId(UUID.fromString("d829059c-c747-11ed-afa1-0242ac120001"))==null) {
            Category p7 = new Category(UUID.fromString("d829059c-c747-11ed-afa1-0242ac120001"),"peixe","Peixe");
            repository.save(p7);
        }

        if (repository.getByCategoryId(UUID.fromString("d8290718-c747-11ed-afa1-0242ac120001"))==null) {
            Category p8 = new Category(UUID.fromString("d8290718-c747-11ed-afa1-0242ac120001"),"legum","Leguminosas");
            repository.save(p8);
        }

        if (repository.getByCategoryId(UUID.fromString("d8290830-c747-11ed-afa1-0242ac120001"))==null) {
            Category p9 = new Category(UUID.fromString("d8290830-c747-11ed-afa1-0242ac120001"),"agua","Agua");
            repository.save(p9);
        }

        if (repository.getByCategoryId(UUID.fromString("d8290a9c-c747-11ed-afa1-0242ac120001"))==null) {
            Category p10 = new Category(UUID.fromString("d8290a9c-c747-11ed-afa1-0242ac120001"),"molh","Molhos");
            repository.save(p10);
        }

        if (repository.getByCategoryId(UUID.fromString("d8290c36-c747-11ed-afa1-0242ac120001"))==null) {
            Category p11 = new Category(UUID.fromString("d8290c36-c747-11ed-afa1-0242ac120001"),"refri","Refrigerantes");
            repository.save(p11);
        }


    }

}
