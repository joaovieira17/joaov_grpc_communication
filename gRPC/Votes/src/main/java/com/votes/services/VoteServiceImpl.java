package com.votes.services;

import com.votes.model.Review;
import com.votes.model.User;
import com.votes.model.Vote;
import com.votes.repositories.HttpReviewRepository;
import com.votes.repositories.ReviewRepository;
import com.votes.repositories.UserRepository;
import com.votes.repositories.VoteRepository;
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

    @Autowired
    private ReviewRepository repository2;

    private HttpReviewRepository httpReviewRepository=new HttpReviewRepository();

    HttpRequestHelper helper = new HttpRequestHelper();
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
    public void updateVotes(Vote vote, UUID reviewId) throws IOException, InterruptedException {

        if (httpReviewRepository.isReview(reviewId) && vote.isVote()){
            httpReviewRepository.upVoteReview(reviewId);
        }
        else if(httpReviewRepository.isReview(reviewId) && !vote.isVote()){
            httpReviewRepository.downVoteReview(reviewId);
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
