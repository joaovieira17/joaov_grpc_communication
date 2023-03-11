package com.votes.repositories;

import com.votes.model.Review;
import com.votes.services.HttpRequestHelper;

import java.io.IOException;
import java.util.UUID;

public class HttpReviewRepository {

    HttpRequestHelper helper = new HttpRequestHelper();

    public boolean isReview(UUID reviewId) throws IOException, InterruptedException {
        Review review=helper.getReview(reviewId);
        if (review==null){
            return false;
        }
        else {
            return true;
        }
    }

    public Review findReviewById(UUID reviewId) throws IOException, InterruptedException {

        Review review= helper.getReview(reviewId);
        return review;

    }

    public void upVoteReview(UUID reviewId) throws IOException, InterruptedException{
        helper.upVoteReview(reviewId);
    }

    public void downVoteReview(UUID reviewId) throws IOException, InterruptedException{
        helper.downVoteReview(reviewId);
    }

}
