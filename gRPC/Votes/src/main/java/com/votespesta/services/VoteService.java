package com.votespesta.services;

import com.votespesta.model.Vote;

import java.io.IOException;
import java.util.UUID;

public interface VoteService {

     String voteInReview (Vote vote);

     boolean getVoteExistence (UUID reviewId);

     Vote getVote(UUID reviewId);

}
