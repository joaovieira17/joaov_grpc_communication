package com.votespesta.services;

import com.votespesta.model.Review;
import com.votespesta.model.Vote;

import java.io.IOException;
import java.util.UUID;

public interface VoteService {

    public boolean updateVoteReview (Vote vote) throws IOException, InterruptedException;

    public void updateVotes(Vote vote, Review review) throws IOException, InterruptedException;

    boolean goodToVote(UUID reviewId) throws IOException, InterruptedException;

    public boolean getVoteExistence (UUID reviewId);

    public Vote getVote(UUID reviewId);


}
