package com.reviews.controllers;

import com.reviews.model.RatingFrequency;
import com.reviews.model.Review;
import com.reviews.model.ReviewDTO;
import com.reviews.services.ReviewService;
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

    @Operation(summary = "Gets a specific review by its reviewId")
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getByReviewId(@PathVariable("reviewId") final UUID reviewId) {
        final Review review = service.getReview(reviewId);
        return ResponseEntity.ok().body(review);
    }

    @Operation(summary = "Gets a product's aggregated rating and frequency")
    @GetMapping(value = "/{sku}/rating")
    public RatingFrequency getRatingFrequency(@PathVariable("sku") final String sku) throws IOException, InterruptedException {
        return service.getRatingFrequencyOfProduct(sku);
    }

    @Operation(summary = "Gets local pending reviews")
    @GetMapping(value = "/pending")
    public Iterable<Review> getLocalPendingReviews()  {
        return service.getLocalPendingReviews();
    }


    @Operation(summary = "Approve or Reject a pending review")
    @PutMapping(value = "/{reviewId}/approve/{reviewStatus}")
    public ResponseEntity<String> approveRejectReview(@PathVariable("reviewId") final UUID reviewId, @PathVariable ("reviewStatus") final boolean reviewStatus){
        boolean status = service.approveRejectReview(reviewId,reviewStatus);
        if(!status){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"The review id you gave it's not associated with a review or this is not in PENDING status");

        }else
            return ResponseEntity.ok("O status da review foi mudado");
    }

    @Operation(summary = "Create a review")
    @PostMapping(value = "/{sku}/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Review> createReview(@Valid @RequestBody final ReviewDTO rev, @PathVariable final String sku) throws IOException, InterruptedException {
        final Review review = service.create(rev, sku);
        return ResponseEntity.ok(review);
    }

    @Operation(summary = "Increase review upVotes")
    @PutMapping(value = "/upvote/{reviewId}")
    public void upVoteReview(@PathVariable final UUID reviewId){
        service.upVote(reviewId);

    }

    @Operation(summary = "Increase review downVotes")
    @PutMapping(value = "/downvote/{reviewId}")
    //@ResponseStatus(HttpStatus.CREATED)
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
    @GetMapping(value = "/{sku}/date/here")
    public Iterable<Review> getReviewsByProductOrderByDateWithoutPage(@PathVariable("sku") final String sku) throws IOException, InterruptedException {
        return service.getReviewsByProductOrderByDateWithoutPage(sku);
    }

    @Operation(summary = "Get all reviews of a product, sorted by number of votes and reverse chronological publishing date")
    @GetMapping(value = "/{sku}/votes")
    public Iterable<Review> getReviewsByProductOrderByVotesWithoutPage(@PathVariable("sku") final String sku) throws IOException, InterruptedException {
        return service.getReviewsByProductOrderByVotesWithoutPage(sku);
    }

    @Operation(summary = "Gets a specific review by its reviewId")
    @GetMapping(value="/reviewByProduct/{sku}")
    public Iterable<Review> getBySku(@PathVariable("sku") final String sku) throws IOException, InterruptedException {
        return service.getReviewsByProduct(sku);
    }

    @Operation(summary = "Gets all my reviews")
    @GetMapping(value = "/myReview")
    public Iterable<Review> getAllMyLocalReviews() {
        return service.getAllMyLocalReviews();
    }

}


