package com.reviewspesta.model;

import java.util.UUID;

public class ReviewVoteDTO {

    private UUID reviewId;

    private String status;

//Getters And Setters

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
