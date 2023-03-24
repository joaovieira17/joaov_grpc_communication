package com.votespesta.services;

import com.votespesta.model.Review;
import com.votespesta.model.User;
import com.votespesta.model.Vote;
import com.votespesta.repositories.HttpReviewRepository;
import com.votespesta.repositories.UserRepository;
import com.votespesta.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    private HttpReviewRepository httpReviewRepository=new HttpReviewRepository();

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean getVoteExistence(UUID reviewId){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        User user = userRepository.findByUsername(username);

        Vote voteValue = repository.findByUserAndId(user.getId(), reviewId);

        if (voteValue!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateVoteReview (Vote vote) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        User user = userRepository.findByUsername(username);
        vote.setUserId(user.getId());

        try {
            Vote voteValue = repository.findByUserAndId(user.getId(), vote.getReviewId());
            if(voteValue == null){
                repository.save(vote);
                return false;
            }else{
                return true;
            }
        }catch(NullPointerException e){
            //repository.save(vote);
            return true;
        }
    }


    @Override
    public void updateVotes(Vote vote, Review review) throws IOException, InterruptedException {

        if (httpReviewRepository.isReview(review.getReviewId()) && vote.isVote()){
            httpReviewRepository.upVoteReview(review.getReviewId());
        }
        else if(httpReviewRepository.isReview(review.getReviewId()) && !vote.isVote()){
            httpReviewRepository.downVoteReview(review.getReviewId());
        }
        else{
            throw new IllegalArgumentException("A review não existe");
        }
    }

    @Override
    public boolean goodToVote(UUID reviewId) throws IOException, InterruptedException {
        if (httpReviewRepository.isReview(reviewId)){
        Review review=httpReviewRepository.findReviewById(reviewId);

        return Objects.equals(review.getStatus(), "APPROVED");
        }else{
            throw new IOException("This review does not exists");
       }
    }

    @Override
    public Vote getVote(UUID reviewId){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        User user = userRepository.findByUsername(username);

        Vote vote = repository.findByUserAndId(user.getId(), reviewId);


        if(vote!=null) {
            return vote;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vote Not Found");
    }
}
