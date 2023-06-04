package com.votespesta.services;

import com.joao.reviews.ReviewResponse;
import com.votespesta.grpcService.ReviewGrpcService;
import com.votespesta.grpcService.VoteGrpcService;
import com.votespesta.model.Vote;
import com.votespesta.repositories.VoteRepository;
import com.votespesta.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;


@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    private final ReviewGrpcService reviewGrpcService;

    private final VoteGrpcService voteGrpcService;

    @Autowired
    private JwtUtils jwtUtils;

    public VoteServiceImpl(ReviewGrpcService reviewGrpcService, VoteGrpcService voteGrpcService) {
        this.reviewGrpcService = reviewGrpcService;
        this.voteGrpcService = voteGrpcService;
    }

    @Override
    public boolean getVoteExistence(UUID reviewId){

        Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));

        Vote voteValue = repository.findByUserAndId(userId, reviewId);

        if (voteValue!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String voteInReview (Vote vote) {

        Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));
        vote.setUserId(userId);

        ReviewResponse reviewResponse = reviewGrpcService.getReview(vote.getReviewId());

        if (reviewResponse.getCode()!=200){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found");
        }

        Vote voteValue = repository.findByUserAndId(userId, vote.getReviewId());
        if(voteValue == null){
                repository.save(vote);
                voteGrpcService.updateVote(vote.getReviewId(),vote.isVote());
            }else{
                throw new ResponseStatusException(HttpStatus.CONFLICT,"You have already voted on this review");
            }
        return "Vote has changed";

    }


    @Override
    public Vote getVote(UUID reviewId){

        Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));

        Vote vote = repository.findByUserAndId(userId, reviewId);

        if(vote!=null) {
            return vote;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vote Not Found");
    }
}
