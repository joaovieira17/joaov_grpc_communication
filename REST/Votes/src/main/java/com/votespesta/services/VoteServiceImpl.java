package com.votespesta.services;

import com.votespesta.model.Vote;
import com.votespesta.repositories.VoteRepository;
import com.votespesta.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    private HttpRequestHelper helper = new HttpRequestHelper();

    @Autowired
    private JwtUtils jwtUtils;

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
    public String voteInReview (Vote vote) throws IOException, InterruptedException {

        Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));
        vote.setUserId(userId);

        if (!helper.reviewExistence(vote.getReviewId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found");
        }

        Vote voteValue = repository.findByUserAndId(userId, vote.getReviewId());
        if(voteValue == null){
                repository.save(vote);
                if(vote.isVote()){
                    helper.upVoteReview(vote.getReviewId());
                }
                else if (!vote.isVote()){
                    helper.downVoteReview(vote.getReviewId());
                }
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

    @Override
    public List<Vote> getAllMyVotes() {
        Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));

        return repository.getAllMyVotes(userId);
    }
}
