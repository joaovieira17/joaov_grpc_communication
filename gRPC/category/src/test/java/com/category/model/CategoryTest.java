package com.category.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryTest {

    @Test
    void ensurePublicKeyHasCorrectSize() {
        Category category = new Category();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> category.setPublicKey("abcdef"));
        assertEquals("'publicKey' has a maximum of 5 characters", exception.getMessage());

        //assertThrows(IllegalArgumentException.class, () -> new Category("abcdefg","Fruta Madura"));
    }

    @Test
    void ensurePublicKeyHasCorrectSize2() {
        Category category = new Category();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> category.setPublicKey("ab"));
        assertEquals("'publicKey' has a minimum of 3 characters", exception.getMessage());

        //assertThrows(IllegalArgumentException.class, () -> new Category("abcdefg","Fruta Madura"));
    }

    @Test
    void ensurePublicKeyIsNotEmpty() {
        Category category = new Category();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> category.setPublicKey(""));
        assertEquals("'publicKey' is a mandatory attribute of Category", exception.getMessage());
    }

    @Test
    void ensurePublicKeyIsAlphaNumeric() {
        Category category = new Category();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> category.setPublicKey("abc@"));
        assertEquals("'publicKey' has invalid characters", exception.getMessage());
    }

    @Test
    void ensurePublicKeyMustNotBeBlankSpaces() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Category("    ","Fruta Madura"));
        assertEquals("'publicKey' cannot have only white spaces", exception.getMessage());
    }

    @Test
    void ensurePublicKeyIsAccepted() {
        Category category = new Category("abcde","Fruta Madura");
        assertEquals("abcde",category.getPublicKey());
    }

    @Test
    void ensureNameIsNotEmpty() {
        Category category = new Category();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> category.setName(""));
        assertEquals("'Name' is a mandatory attribute of Category", exception.getMessage());
    }

    @Test
    void ensureNameMustNotBeBlankSpaces() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Category("abcde","      "));
        assertEquals("'Name' cannot have only white spaces", exception.getMessage());
    }

    @Test
    void ensureNameHasCorrectSize() {
        Category category = new Category();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> category.setName("asjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgas"));
        assertEquals("'Name' has a maximum of 40 characters", exception.getMessage());
    }

    @Test
    void ensureDesignationIsAccepted() {
        Category category = new Category();
        category.setName("Fruta Madura");
        assertEquals("Fruta Madura", category.getName());
        Category category2 = new Category("abcde", "Fruta Madura");
        assertEquals("Fruta Madura",category2.getName());
    }


    @Test
    void ensureIdMustNotBeBlankSpaces() {
        assertThrows(IllegalArgumentException.class, () -> new Category(UUID.fromString("   "),"abcde","Fruta Madura"));
    }

    @Test
    void ensureIdIsNotEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Category(UUID.fromString(""),"abcde","Fruta Madura"));
    }

    @Test
    void ensureIdIsAccepted() {
        Category category = new Category(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"),"abcde","Fruta Madura");
        assertEquals(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"), category.getCategoryId());
    }

    @Test
    void ensureEverythingIsInPlace() {
        Category category = new Category(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"),"abcde","Fruta Madura");
        assertEquals(UUID.fromString("d828f1ce-c747-11ed-afa1-0242ac120001"), category.getCategoryId());
        assertEquals("abcde", category.getPublicKey());
        assertEquals("Fruta Madura", category.getName());
    }



}

