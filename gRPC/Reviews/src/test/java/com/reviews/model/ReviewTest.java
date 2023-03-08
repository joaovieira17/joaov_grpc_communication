package com.reviews.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

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
    void ensureRatingIsCorrectNumber(){
        Review rev = new Review();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->rev.setRating(6));
        assertEquals("Rating out of range", exception.getMessage());
    }

    @Test
    void ensureStatusIsNotEmpty(){
        Review rev = new Review();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->rev.setStatus(""));
        assertEquals("Status is a mandatory attribute", exception.getMessage());
    }

    @Test
    void ensureDateIsNotNull(){
        Review rev = new Review();
        Date date = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->rev.setDate(date));
        assertEquals("Date is a mandatory attribute", exception.getMessage());
    }

    @Test
    void ensureFunFactIsNotEmpty(){
        Review rev = new Review();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->rev.setFunFact(""));
        assertEquals("Fun Fact Empty", exception.getMessage());
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
    void ensureRatingIsAccepted(){
        Review rev = new Review();
        rev.setRating(5);
        assertEquals(5, rev.getRating());
    }

    @Test
    void ensureStatusIsAccepted(){
        Review rev = new Review();
        rev.setStatus("APPROVED");
        assertEquals("APPROVED", rev.getStatus());
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
}
