package com.votespesta.controllers;

import com.votespesta.model.Review;
import com.votespesta.model.Vote;
import com.votespesta.services.HttpRequestHelper;
import com.votespesta.services.VoteServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@Tag(name = "Vote", description = "Endpoints for managing vote")
@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteServiceImpl service;


    private HttpRequestHelper helper=new HttpRequestHelper();


    @Operation(summary = "Creates a vote")
    @PostMapping(value = "/updateVote")
    public ResponseEntity<String> upVoteReview(@Valid @RequestBody final Vote vote ) throws IOException, InterruptedException {
        Review review = helper.getReview(vote.getReviewId());
        if (review!=null) {
        boolean status = service.goodToVote(review.getReviewId());
        if (status) {
            boolean haveVoted = service.updateVoteReview(vote);
            if (!haveVoted) {
                service.updateVotes(vote, review);
                return ResponseEntity.ok("Vote changed");
            } else
                throw new ResponseStatusException(HttpStatus.CONFLICT,"You have already voted on this review");
        }else
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This review isn't approved yet");
        }else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found");
    }

    @Operation(summary = "Verifies if vote exist")
    @GetMapping(value = "/getVoteExistence/{reviewId}")
    public boolean VoteExistence(@PathVariable("reviewId")final UUID reviewId) {
        return service.getVoteExistence(reviewId);
    }

    @Operation(summary = "Get Vote")
    @GetMapping(value = "/getVoteData/{reviewId}")
    public Vote VoteData(@PathVariable("reviewId")final UUID reviewId){
        return service.getVote(reviewId);
    }

}
