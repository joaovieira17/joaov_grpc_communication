package com.products.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void ensureSkuHasCorrectSize() {
        //ALTERNATIVA-Como o sku não pode ser alterado o "setSku" não pode ser utilizado
        /*Product product = new Product();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> product.setSku("1234567"));
        assertEquals("'SKU' has not a correct format", exception.getMessage());*/
        assertThrows(IllegalArgumentException.class, () -> new Product("1234567","colher","top",""));

    }

    @Test
    void ensureSkuIsNotEmpty() {
        //ALTERNATIVA-Como o sku não pode ser alterado o "setSku" não pode ser utilizado
        /*Product product = new Product();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> product.setSku(""));
        assertEquals("'SKU' has not a correct format", exception.getMessage());*/

        assertThrows(IllegalArgumentException.class, () -> new Product("","colher","top",""));

    }

    @Test
    void ensureSkuIsAlphaNumeric() {
        //ALTERNATIVA-Como o sku não pode ser alterado o "setSku" não pode ser utilizado
       /* Product product = new Product();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> product.setSku("12345678912@"));
        assertEquals("'SKU' has not a correct format", exception.getMessage());*/

        assertThrows(IllegalArgumentException.class, () -> new Product("12345678912@","colher","top",""));
    }

    @Test
    void ensureSkuMustNotBeBlankSpaces() {
        assertThrows(IllegalArgumentException.class, () -> new Product("     ","fasfass","dsafasfa",""));
    }

    @Test
    void ensureSkuIsAccepted() {
        Product product = new Product("123456789171","Batata","Maravilha","");
        //product.setSku("123456789123"); //Como o sku não pode ser alterado o "setSku" não pode ser utilizado
        assertEquals("123456789171",product.getSku());
    }

    @Test
    void ensureDesignationIsNotEmpty() {
        Product product = new Product();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> product.setDesignation(""));
        assertEquals("'Designation' is a mandatory attribute of Product", exception.getMessage());
    }

    @Test
    void ensureDesignationMustNotBeBlankSpaces() {
        assertThrows(IllegalArgumentException.class, () -> new Product("123456789171","     ","dsafasfa",""));
    }

    @Test
    void ensureDesignationHasCorrectSize() {
        Product product = new Product();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> product.setDesignation("asjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgas"));
        assertEquals("'Designation' has a maximum of 50 characters", exception.getMessage());
    }

    @Test
    void ensureDesignationIsAccepted() {
        Product product = new Product();
        product.setDesignation("Colher de sopa");
        assertEquals("Colher de sopa", product.getDesignation());
        Product product1 = new Product("123456789171","Colher de sopa","é de sopa","");
        assertEquals("Colher de sopa",product1.getDesignation());
    }

    @Test
    void ensureDescriptionHasCorrectSize() {
        Product product = new Product();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> product.setDescription("asjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgasasjfaosufhaosgufhasgasjgoaguhagkoijafkaoifhasfifaoagsgas\n"));
        assertEquals("'Description' has a maximum of 1024 characters", exception.getMessage());
    }

    @Test
    void ensureDescriptionIsNotEmpty() {
        Product product = new Product();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> product.setDescription(""));
        assertEquals("'Description' is a mandatory attribute of Product", exception.getMessage());
    }

    @Test
    void ensureDescriptionMustNotBeBlankSpaces() {
        assertThrows(IllegalArgumentException.class, () -> new Product("123456789171","Colher","     ",""));
    }

    @Test
    void ensureDescriptionIsAccepted() {
        Product product = new Product();
        product.setDescription("Incrível, barato e a melhor colher de sempre que faz o trabalho como todas as outras colheres");
        assertEquals("Incrível, barato e a melhor colher de sempre que faz o trabalho como todas as outras colheres", product.getDescription());
        Product product1 = new Product("123456789171","Colher","Incrível, barato e a melhor colher de sempre que faz o trabalho como todas as outras colheres","");
        assertEquals("Incrível, barato e a melhor colher de sempre que faz o trabalho como todas as outras colheres", product1.getDescription());
    }

}
