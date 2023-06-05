package com.sandwich.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SandwichTests {

    Ingredient tomate=new Ingredient("tomate", "Fruta");
    Ingredient alface=new Ingredient("alface", "Horticolas");

    @Test
    void ensurePublicKeyHasCorrectSize() {
        Sandwich sandwich = new Sandwich();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sandwich.setPublicKey("abcdef"));
        assertEquals("'publicKey' has a maximum of 5 characters", exception.getMessage());
    }

    @Test
    void ensurePublicKeyHasCorrectSize2() {
        Sandwich sandwich = new Sandwich();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sandwich.setPublicKey("ab"));
        assertEquals("'publicKey' has a minimum of 3 characters", exception.getMessage());
    }

    @Test
    void ensurePublicKeyIsNotEmpty() {
        Sandwich sandwich = new Sandwich();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sandwich.setPublicKey(""));
        assertEquals("'publicKey' is a mandatory attribute of Ingredient", exception.getMessage());
    }

    @Test
    void ensurePublicKeyIsAlphaNumeric() {
        Sandwich sandwich = new Sandwich();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sandwich.setPublicKey("abc@"));
        assertEquals("'publicKey' has invalid characters", exception.getMessage());
    }

    @Test
    void ensurePublicKeyMustNotBeBlankSpaces() {
        List<Ingredient> list1 = new ArrayList<>();
        list1.add(tomate);
        list1.add(alface);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Sandwich("    ","Teste","Teste", list1));
        assertEquals("'publicKey' cannot have white spaces", exception.getMessage());
    }

    @Test
    void ensurePublicKeyIsAccepted() {
        List<Ingredient> list1 = new ArrayList<>();
        list1.add(tomate);
        list1.add(alface);
        Sandwich sandwich = new Sandwich("abcde","Teste","Teste", list1);
        assertEquals("abcde",sandwich.getPublicKey());
    }


    @Test
    void ensureNameIsNotEmpty() {
        Sandwich sandwich = new Sandwich();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sandwich.setDesignation(""));
        assertEquals("'Designation' is a mandatory attribute of Sandwich", exception.getMessage());
    }

    @Test
    void ensureNameMustNotBeBlankSpaces() {
        List<Ingredient> list1 = new ArrayList<>();
        list1.add(tomate);
        list1.add(alface);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Sandwich("abcde","     ","Teste", list1));
        assertEquals("'Designation' cannot have only white spaces", exception.getMessage());
    }

    @Test
    void ensureNameHasCorrectSize() {
        Sandwich sandwich = new Sandwich();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sandwich.setDesignation("asjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgas"));
        assertEquals("'Designation' has a maximum of 50 characters", exception.getMessage());
    }

    @Test
    void ensureDesignationIsAccepted() {
        List<Ingredient> list1 = new ArrayList<>();
        list1.add(tomate);
        list1.add(alface);
        Sandwich sandwich = new Sandwich();
        sandwich.setDesignation("Teste");
        assertEquals("Teste", sandwich.getDesignation());
        Sandwich sandwich1 = new Sandwich("abcde", "Teste2","Teste",list1);
        assertEquals("Teste2",sandwich1.getDesignation());
    }


    @Test
    void ensureIdMustNotBeBlankSpaces() {
        List<Ingredient> list1 = new ArrayList<>();
        list1.add(tomate);
        list1.add(alface);
        assertThrows(IllegalArgumentException.class, () -> new Sandwich(UUID.fromString("   "),"abcde","Teste","Teste",list1));
    }

    @Test
    void ensureIdIsNotEmpty() {
        List<Ingredient> list1 = new ArrayList<>();
        list1.add(tomate);
        list1.add(alface);
        assertThrows(IllegalArgumentException.class, () -> new Sandwich(UUID.fromString(""),"abcde","Teste","Teste",list1));
    }

    @Test
    void ensureIdIsAccepted() {
        List<Ingredient> list1 = new ArrayList<>();
        list1.add(tomate);
        list1.add(alface);
        Sandwich sandwich = new Sandwich(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"),"abcde","Teste","Teste",list1);
        assertEquals(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"), sandwich.getSandwichId());
    }

    @Test
    void ensureDescriptionIsNotEmpty() {
        Sandwich sandwich = new Sandwich();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sandwich.setDescription(""));
        assertEquals("'Description' is a mandatory attribute of Sandwich", exception.getMessage());
    }

    @Test
    void ensureDescriptionMustNotBeBlankSpaces() {
        Sandwich sandwich = new Sandwich();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sandwich.setDescription("    "));
        assertEquals("'Description' cannot have only white spaces", exception.getMessage());
    }

    @Test
    void ensureDescriptionHasCorrectSize() {
        Sandwich sandwich = new Sandwich();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sandwich.setDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertEquals("'Description' has a maximum of 1024 characters", exception.getMessage());
    }

    @Test
    void ensureDescriptionIsAccepted() {
        List<Ingredient> list1 = new ArrayList<>();
        list1.add(tomate);
        list1.add(alface);
        Sandwich sandwich = new Sandwich();
        sandwich.setDescription("Teste");
        assertEquals("Teste", sandwich.getDescription());
        Sandwich sandwich1 = new Sandwich("abcde", "Teste","Teste2",list1);
        assertEquals("Teste2",sandwich1.getDescription());
    }


    @Test
    void ensureListOfIngredientsIsNotAccepted() {
        List<Ingredient> list1 = new ArrayList<>();
        list1.add(tomate);
        //list1.add(alface);

        Sandwich sandwich = new Sandwich();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sandwich.setListOfIngredients(list1));
        assertEquals("'listOfIngredients' is a mandatory attribute of Sandwich and you must choose at least two. For example: Bread and cheese", exception.getMessage());

    }

    @Test
    void ensureListOfIngredientsIsAccepted() {
        List<Ingredient> list1 = new ArrayList<>();
        list1.add(tomate);
        list1.add(alface);

        Sandwich sandwich = new Sandwich();
        sandwich.setListOfIngredients(list1);
        assertEquals(list1, sandwich.getListOfIngredients());
    }

    @Test
    void ensureEverythingIsInPlace() {
        List<Ingredient> list1 = new ArrayList<>();
        list1.add(tomate);
        list1.add(alface);
        Sandwich sandwich = new Sandwich(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"),"abcde","Teste","Teste2",list1);
        assertEquals(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"), sandwich.getSandwichId());
        assertEquals("abcde", sandwich.getPublicKey());
        assertEquals("Teste", sandwich.getDesignation());
        assertEquals("Teste2", sandwich.getDescription());
        assertEquals(list1, sandwich.getListOfIngredients());
    }


}
