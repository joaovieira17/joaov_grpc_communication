package com.reviewspesta.controllers;

import com.reviewspesta.model.RatingFrequency;
import com.reviewspesta.model.Review;
import com.reviewspesta.model.ReviewDTO;
import com.reviewspesta.model.ReviewVoteDTO;
import com.reviewspesta.services.ReviewService;
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

@Tag(name = "Reviews", description = "Endpoints for managing reviews")
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @Operation(summary = "Gets every review ")
    @GetMapping("/list")
    public Iterable<Review> getEveryReview() {
        return service.getEveryReview();
    }

    @Operation(summary = "Gets a specific review by its reviewId")
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getByReviewId(@PathVariable("reviewId") final UUID reviewId) {
        final Review review = service.getReview(reviewId);
        return ResponseEntity.ok().body(review);
    }

    @Operation(summary = "Gets a product's aggregated rating and frequency")
    @GetMapping(value = "/{sandwichId}/rating")
    public RatingFrequency getRatingFrequency(@PathVariable("sandwichId") final UUID sandwichId) throws IOException, InterruptedException {
        return service.getRatingFrequencyOfSandwich(sandwichId);
    }


    @Operation(summary = "Create a review")
    @PostMapping(value = "/{sandwichId}/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Review> createReview(@Valid @RequestBody final ReviewDTO rev, @PathVariable final UUID sandwichId) throws IOException, InterruptedException {
        final Review review = service.create(rev, sandwichId);
        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    }

    @Operation(summary = "Increase review upVotes")
    @PutMapping(value = "/upvote/{reviewId}")
    public void upVoteReview(@PathVariable final UUID reviewId){
        service.upVote(reviewId);

    }

    @Operation(summary = "Increase review downVotes")
    @PutMapping(value = "/downvote/{reviewId}")
    public void downVoteReview(@PathVariable final UUID reviewId){
        service.downVote(reviewId);
    }


    @Operation(summary = "Withdraws a review")
    @DeleteMapping(value = "/{reviewId}/remove")
    public ResponseEntity<String> deleteByReviewId(@PathVariable ("reviewId") final UUID reviewId){
        Review review = service.getReview(reviewId);
        if (review!=null) {
            boolean belongs = service.belongsToUser(review);
            if(belongs) {
                boolean delete = service.goodToDel(review);
                if (delete) {
                    return ResponseEntity.ok("Review deleted");
                } else
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Review can't be deleted because have up or down votes");
            }else
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Review does not belong to you");
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Review Not Found");

    }

    @Operation(summary = "Verifies if review exists based on its reviewId")
    @GetMapping("/{reviewId}/reviewExistence")
    public boolean doesReviewExist(@PathVariable("reviewId")final UUID reviewId){
        return service.reviewExistence(reviewId);
    }

    @Operation(summary = "Get all database reviews associated with one product ordered by chronologival date without page")
    @GetMapping(value = "/{sandwichId}/date/here")
    public Iterable<Review> getReviewsByProductOrderByDateWithoutPage(@PathVariable("sandwichId") final UUID sandwichId) throws IOException, InterruptedException {
        return service.getReviewsBySandwichOrderByDateWithoutPage(sandwichId);
    }

    @Operation(summary = "Get all reviews of a product, sorted by number of votes and reverse chronological publishing date")
    @GetMapping(value = "/{sandwichId}/votes")
    public Iterable<Review> getReviewsByProductOrderByVotesWithoutPage(@PathVariable("sandwichId") final UUID sandwichId) throws IOException, InterruptedException {
        return service.getReviewsBySandwichOrderByVotesWithoutPage(sandwichId);
    }

    @Operation(summary = "Gets a specific review by its reviewId")
    @GetMapping(value="/reviewBySandwich/{sandwichId}")
    public Iterable<Review> getBySandwich(@PathVariable("sandwichId") final UUID sandwichId) throws IOException, InterruptedException {
        return service.getReviewsBySandwich(sandwichId);
    }

    @Operation(summary = "Gets all my reviews")
    @GetMapping(value = "/myReview")
    public Iterable<Review> getAllMyReviews() {
        return service.getAllMyReviews();
    }

    @Operation(summary = "Get Reviews by a specific language")
    @GetMapping(value = "/reviewByLanguage/{language}")
    public Iterable<Review> getByLanguage(@PathVariable("language") final String language){
        return service.getReviewByLanguage(language);
    }

}


