package com.ingredient.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredientTest {

    Category category = new Category("test");

    @Test
    void ensurePublicKeyHasCorrectSize() {
        Ingredient ingredient = new Ingredient();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> ingredient.setPublicKey("abcdef"));
        assertEquals("'publicKey' has a maximum of 5 characters", exception.getMessage());
    }

    @Test
    void ensurePublicKeyHasCorrectSize2() {
        Ingredient ingredient = new Ingredient();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> ingredient.setPublicKey("ab"));
        assertEquals("'publicKey' has a minimum of 3 characters", exception.getMessage());
    }

    @Test
    void ensurePublicKeyIsNotEmpty() {
        Ingredient ingredient = new Ingredient();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> ingredient.setPublicKey(""));
        assertEquals("'publicKey' is a mandatory attribute of Ingredient", exception.getMessage());
    }

    @Test
    void ensurePublicKeyIsAlphaNumeric() {
        Ingredient ingredient = new Ingredient();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> ingredient.setPublicKey("abc@"));
        assertEquals("'publicKey' has invalid characters", exception.getMessage());
    }

    @Test
    void ensurePublicKeyMustNotBeBlankSpaces() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Ingredient("    ","Fruta Madura", category));
        assertEquals("'publicKey' cannot have white spaces", exception.getMessage());
    }

    @Test
    void ensurePublicKeyIsAccepted() {
        Ingredient ingredient = new Ingredient("abcde","Fruta Madura", category);
        assertEquals("abcde",ingredient.getPublicKey());
    }

    @Test
    void ensureNameIsNotEmpty() {
        Ingredient ingredient = new Ingredient();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> ingredient.setName(""));
        assertEquals("'Name' is a mandatory attribute of Ingredient", exception.getMessage());
    }

    @Test
    void ensureNameMustNotBeBlankSpaces() {
        assertThrows(IllegalArgumentException.class, () -> new Ingredient("abcde","      ",category));
    }

    @Test
    void ensureNameHasCorrectSize() {
        Ingredient ingredient = new Ingredient();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> ingredient.setName("asjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgas"));
        assertEquals("'Name' has a maximum of 25 characters", exception.getMessage());
    }

    @Test
    void ensureDesignationIsAccepted() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Fruta Madura");
        assertEquals("Fruta Madura", ingredient.getName());
        Ingredient ingredient1 = new Ingredient("abcde", "Fruta Madura",category);
        assertEquals("Fruta Madura",ingredient1.getName());
    }


    @Test
    void ensureIdMustNotBeBlankSpaces() {
        assertThrows(IllegalArgumentException.class, () -> new Ingredient(UUID.fromString("   "),"abcde","Fruta Madura",category));
    }

    @Test
    void ensureIdIsNotEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Ingredient(UUID.fromString(""),"abcde","Fruta Madura",category));
    }

    @Test
    void ensureIdIsAccepted() {
        Ingredient ingredient = new Ingredient(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"),"abcde","Fruta Madura",category);
        assertEquals(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"), ingredient.getIngredientId());
    }

    @Test
    void ensureCategoryIsNotEmpty() {
        Category category = new Category();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> category.setCategoryName(""));
        assertEquals("'Name' is a mandatory attribute of Category", exception.getMessage());
    }

    @Test
    void ensureNameOfCategoryMustNotBeBlankSpaces() {
        assertThrows(IllegalArgumentException.class, () -> new Category("     "));
    }

    @Test
    void ensureNameOfCategoryHasCorrectSize() {
        Category category = new Category();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> category.setCategoryName("asjfaosufhaosgufhasgasfasfjashfiasfçljafalehaeignaiogeafºljcnaogsfalrqurqwlmjfouqfjgoaguhagkoijafkaoifhasfifaoagsgaasalkfohasuiofahsiufgaskfnausfs"));
        assertEquals("'Name' has a maximum of 50 characters", exception.getMessage());
    }

    @Test
    void ensureEverythingIsInPlace() {
        Ingredient ingredient = new Ingredient(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"),"abcde","Fruta Madura",category);
        assertEquals(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"), ingredient.getIngredientId());
        assertEquals("abcde", ingredient.getPublicKey());
        assertEquals("Fruta Madura", ingredient.getName());
        assertEquals("test", ingredient.getCategory().getCategoryName());
    }
}
