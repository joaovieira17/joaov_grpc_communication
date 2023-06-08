package com.votespesta.controllers;

import com.votespesta.model.Vote;
import com.votespesta.services.VoteServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@Tag(name = "Vote", description = "Endpoints for managing vote")
@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteServiceImpl service;

    @Operation(summary = "Creates a vote")
    @PostMapping(value = "/updateVote")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> upVoteReview(@Valid @RequestBody final Vote vote ) {
        String voteInReview = service.voteInReview(vote);
        return ResponseEntity.status(HttpStatus.CREATED).body(voteInReview);
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

    @GetMapping(value = "/MyVotes")
    public Iterable<Vote> getMyVotes(){
        return service.getAllMyVotes();
    }


}
