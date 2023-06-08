package com.votespesta.services;

import com.votespesta.model.Vote;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface VoteService {

     String voteInReview (Vote vote) throws IOException, InterruptedException;

     boolean getVoteExistence (UUID reviewId);

     Vote getVote(UUID reviewId);

     List<Vote> getAllMyVotes();

}
