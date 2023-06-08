package com.votespesta.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class VotesTests {

    @Test
    void ensureUserIsNotNull(){
        Vote vote = new Vote();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->vote.setUserId(null));
        assertEquals("User Id is a mandatory attribute of a Vote", exception.getMessage());
    }

    @Test
    void ensureUserIsAccepted(){
        Vote vote = new Vote();
        vote.setUserId(5L);
        assertEquals(5, vote.getUserId());
    }

    @Test
    void ensureReviewIdIsNotNull(){
        Vote vote = new Vote();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->vote.setReviewId(null));
        assertEquals("Review Id is a mandatory attribute of a Vote", exception.getMessage());
    }

    @Test
    void ensureReviewIdIsNotBlackSpaces(){
        Vote vote = new Vote();
        assertThrows(IllegalArgumentException.class, () ->vote.setReviewId(UUID.fromString("   ")));
    }

    @Test
    void ensureReviewIdIsUUID(){
        Vote vote = new Vote();
        assertThrows(IllegalArgumentException.class, () ->vote.setReviewId(UUID.fromString("afshf9")));
    }

    @Test
    void ensureReviewIdIsAccepted(){
        Vote vote = new Vote();;
        vote.setReviewId(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"));
        assertEquals(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"), vote.getReviewId());
    }

    @Test
    void ensureEverythingIsCorrectAndInPlace(){
        Vote vote = new Vote(5L,UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"),true);
        assertEquals(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"),vote.getReviewId());
        assertEquals(true, vote.isVote());
        assertEquals(5,vote.getUserId());
        assertNotEquals("",vote.getVoteId().toString());
    }
}
