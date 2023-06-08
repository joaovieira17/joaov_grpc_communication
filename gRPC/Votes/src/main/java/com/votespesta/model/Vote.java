package com.votespesta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Vote")
public class Vote {

    @Id
    @Column(nullable = false, unique = true)
    private UUID voteId = UUID.randomUUID();

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private UUID reviewId;

    @Column(nullable = false)
    private boolean vote;



    public Vote() {

    }

    public Vote(Long userId, UUID reviewId, boolean vote) {
        setUserId(userId);
        setReviewId(reviewId);
        setVote(vote);
    }

    public UUID getVoteId() {
        return voteId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {if (userId == null){
        throw new IllegalArgumentException("User Id is a mandatory attribute of a Vote");
    }

        this.userId = userId;
    }

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        if (reviewId == null || reviewId.toString().isEmpty()){
            throw new IllegalArgumentException("Review Id is a mandatory attribute of a Vote");
        }
        this.reviewId = reviewId;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }


}
