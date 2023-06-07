package com.reviewspesta.model;

import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static com.github.pemistahl.lingua.api.Language.*;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReviewTest {

    @Test
    void ensureTextHasCorrectSize() {
        Review rev = new Review();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> rev.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertEquals("Review Text Length is too big", exception.getMessage());
    }

    @Test
    void ensureReviewTextIsNotWhiteSpaces(){
        Review rev = new Review();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->rev.setText("          "));
        assertEquals("Review Text cannot be white spaces", exception.getMessage());
    }

    @Test
    void ensureTextIsAccepted() {
        Review rev = new Review();
        rev.setText("Produto com muita qualidade");
        assertEquals("Produto com muita qualidade", rev.getText());
    }

    @Test
    void ensureDateIsNotNull(){
        Review rev = new Review();
        Date date = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->rev.setDate(date));
        assertEquals("Date is a mandatory attribute", exception.getMessage());
    }

    @Test
    void ensureDateIsAccepted(){
        Review rev = new Review();
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        rev.setDate(date);

        assertEquals(rev.getDate(),date);
    }

    @Test
    void ensureRatingIsCorrectNumber(){
        Review rev = new Review();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->rev.setRating(6));
        assertEquals("Rating out of range", exception.getMessage());
    }

    @Test
    void ensureRatingIsAccepted(){
        Review rev = new Review();
        rev.setRating(5);
        assertEquals(5, rev.getRating());
    }

    @Test
    void ensureUpVoteIsRegistered(){
        Review rev = new Review();
        rev.setUpVotes(4);
        assertEquals(4, rev.getUpVotes());
    }

    @Test
    void ensureDownVoteIsRegistered(){
        Review rev = new Review();
        rev.setDownVotes(2);
        assertEquals(2, rev.getDownVotes());
    }

    @Test
    void ensureVotesAreUpdated(){
        Review rev = new Review();
        rev.setUpVotes(4);
        rev.updateVote(true);
        assertEquals(5, rev.getUpVotes());
        rev.setDownVotes(2);
        rev.updateVote(false);
        assertEquals(3, rev.getDownVotes());
    }

    @Test
    void ensureFunFactIsNotEmpty(){
        Review rev = new Review();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->rev.setFunFact(""));
        assertEquals("Fun Fact Empty", exception.getMessage());
    }

    @Test
    void ensureFunFactIsAccepted(){
        Review rev = new Review();
        rev.setFunFact("Test even tho the fun fact is automatic generated");
        assertEquals("Test even tho the fun fact is automatic generated", rev.getFunFact());
    }

    @Test
    void ensureUserIsNotNull(){
        Review rev = new Review();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->rev.setUserId(null));
        assertEquals("User Id is a mandatory attribute", exception.getMessage());
    }

    @Test
    void ensureUserIsAccepted(){
        Review rev = new Review();
        rev.setUserId(5L);
        assertEquals(5, rev.getUserId());
    }

    @Test
    void ensureSandwichIdIsNotNull(){
        Review rev = new Review();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->rev.setSandwichId(null));
        assertEquals("Sandwich Id is a mandatory attribute", exception.getMessage());
    }

    @Test
    void ensureSandwichIdIsNotBlackSpaces(){
        Review rev = new Review();
        assertThrows(IllegalArgumentException.class, () ->rev.setSandwichId(UUID.fromString("   ")));
    }

    @Test
    void ensureSandwichIdIsUUID(){
        Review rev = new Review();
        assertThrows(IllegalArgumentException.class, () ->rev.setSandwichId(UUID.fromString("afshf9")));
    }

    @Test
    void ensureSandwichIdIsAccepted(){
        Review rev = new Review();
        rev.setSandwichId(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"));
        assertEquals(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"), rev.getSandwichId());
    }

    @Test
    void ensureLanguageDetectorIsWorking(){
        Review rev = new Review();
        rev.setText("Produto com muita qualidade");
        final LanguageDetector detector = LanguageDetectorBuilder.fromLanguages(ENGLISH, FRENCH, GERMAN, SPANISH, PORTUGUESE).build();
        rev.setLanguage(detector.detectLanguageOf(rev.getText()).toString());
        assertEquals("PORTUGUESE", rev.getLanguage());
    }


    @Test
    void ensureEverythingIsAcceptedAndInPlace(){
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Review rev = new Review(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120001"),date,"Muita Qualidade",5,1,0,"gerado",5L,UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"),"PORTUGUESE");
        assertEquals(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120001"), rev.getReviewId());
        assertEquals(date,rev.getDate());
        assertEquals("Muita Qualidade",rev.getText());
        assertEquals(5,rev.getRating());
        assertEquals(1,rev.getUpVotes());
        assertEquals(0,rev.getDownVotes());
        assertEquals("gerado", rev.getFunFact());
        assertEquals(5,rev.getUserId());
        assertEquals(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"),rev.getSandwichId());
        assertEquals("PORTUGUESE", rev.getLanguage());
    }

    @Test
    void ensureEverythingIsAcceptedAndInPlace2(){
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Review rev = new Review(date,"Muita Qualidade",5,1,0,"gerado",5L,UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"),"PORTUGUESE");
        assertNotEquals("", rev.getReviewId().toString());
        assertEquals(date,rev.getDate());
        assertEquals("Muita Qualidade",rev.getText());
        assertEquals(5,rev.getRating());
        assertEquals(1,rev.getUpVotes());
        assertEquals(0,rev.getDownVotes());
        assertEquals("gerado", rev.getFunFact());
        assertEquals(5,rev.getUserId());
        assertEquals(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"),rev.getSandwichId());
        assertEquals("PORTUGUESE", rev.getLanguage());
        assertEquals(false,rev.goodToDel(rev.getUpVotes(),rev.getDownVotes()));
        rev.setUpVotes(0);
        assertEquals(true,rev.goodToDel(rev.getUpVotes(),rev.getDownVotes()));
    }

}
