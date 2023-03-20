package com.votes.controllers;

import com.joao.reviews.ReviewResponse;
import com.votes.grpcService.ReviewGrpcService;
import com.votes.grpcService.VoteGrpcService;
import com.votes.model.Vote;
import com.votes.services.HttpRequestHelper;
import com.votes.services.VoteServiceImpl;
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

    private final ReviewGrpcService reviewGrpcService;

    private final VoteGrpcService voteGrpcService;

    public VoteController(ReviewGrpcService reviewGrpcService, VoteGrpcService voteGrpcService) {
        this.reviewGrpcService = reviewGrpcService;
        this.voteGrpcService = voteGrpcService;
    }

    @Operation(summary = "Creates a vote")
    @PostMapping(value = "/updateVote")
    public ResponseEntity<String> upVoteReview(@Valid @RequestBody final Vote vote ) throws IOException, InterruptedException {
        //Review review = helper.getReview(vote.getReviewId());
        ReviewResponse reviewResponse = reviewGrpcService.getReview(vote.getReviewId());
        if (reviewResponse.getCode()==200) {
            //boolean status = service.goodToVote(UUID.fromString(reviewResponse.getReviewId()));
                if (reviewResponse.getStatus().equals("APPROVED")) {
                    boolean haveVoted = service.updateVoteReview(vote);
                        if (!haveVoted) {
                            //service.updateVotes(vote, review.getReviewId());
                            voteGrpcService.updateVote(vote.getReviewId(),vote.isVote());
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
