package com.votes.grpcService;

import com.joao.votes.VotesResponse;

import java.util.UUID;

public interface VoteGrpcService {
    VotesResponse updateVote (UUID reviewId, boolean vote);
}
