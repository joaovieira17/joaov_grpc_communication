package com.reviewspesta.services;

import com.reviewspesta.model.*;
//import com.psoftprojectg5.repositories.ProductRepository;
import com.reviewspesta.repositories.ReviewRepository;
import com.reviewspesta.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;


@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private JwtUtils jwtUtils;


    private HttpRequestHelper helper=new HttpRequestHelper();


    @Override
    public List<Review> getEveryReview() {
        return repository.getEveryReview();
    }

    @Override
    public Review getReview(UUID reviewId) {

        Optional<Review> review = Optional.ofNullable(repository.getReviewById(reviewId));
        if (review.isPresent()) {
            return review.get();
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Review Not Found");
    }

    @Override
    public List<Review> getReviewsBySandwich(UUID sandwichId) throws IOException, InterruptedException {

        if (helper.doesSandwichExist(sandwichId)) {

            return repository.getReviewsBySandwich(sandwichId);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sandwich Not Found");
        }

    }

    @Override
    public Review create(ReviewDTO rev, UUID sandwichId) throws IOException, InterruptedException {

        Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));

        List<String> forbiddenWords = List.of("cafe", "morango", "colher");

        for (int i=0; i<forbiddenWords.size();i++){
            if (rev.getText().toLowerCase().contains(forbiddenWords.get(i))){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"You can't use that word:"+ forbiddenWords.get(i));
            }
        }

        if (helper.doesSandwichExist(sandwichId)){
            final Review obj = Review.newFrom(rev,sandwichId,userId);

            obj.setSandwichId(sandwichId);
            return repository.save(obj);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sandwich Not Found");
        }

    }

    @Override
    public RatingFrequency getRatingFrequencyOfSandwich(UUID sandwichId) throws IOException, InterruptedException {
        if(helper.doesSandwichExist(sandwichId)){
            List<Review> reviews = getReviewsBySandwich(sandwichId);
            int rating;
            int one=0, two=0, three=0, four=0, five=0;
            RatingFrequency freq = new RatingFrequency();
            for (int i=0; i< reviews.size(); i++){
                rating=reviews.get(i).getRating();
                if (rating == 1){
                    one = one + 1;
                }
                else if (rating == 2){
                    two = two + 1;
                }
                else if (rating == 3){
                    three = three + 1;
                }
                else if (rating == 4){
                    four = four + 1;
                }
                else if (rating == 5){
                    five = five + 1;
                }
            }
            float globalRating =repository.getAggregatedRating(sandwichId);
            return new RatingFrequency(one, two, three, four, five, globalRating);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sandwich Not Found");
        }
    }

    @Override
    public void updateVotes(Vote vote, Review review){
        review.updateVote(vote.isVote());
        repository.save(review);
    }

    @Override
    public boolean goodToDel(Review review){
        Boolean del = review.goodToDel(review.getUpVotes(),review.getDownVotes());
        if(del) {
            repository.delete(review);
            return true;
        }else
            return false;
    }


    @Override
    public boolean belongsToUser(Review review){

        Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));
        return Objects.equals(review.getUserId(), userId);
    }


    @Override
    public boolean goodToVote(Review review){
        return Objects.equals(review.getStatus(), "APPROVED");
    }

    @Override
    public void upVote(UUID reviewId){
        Review review = repository.getReviewById(reviewId);
        if (review!=null){
            review.updateVote(true);
            repository.save(review);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found");
        }
    }

    @Override
    public void downVote(UUID reviewId){
        Review review = repository.getReviewById(reviewId);
        if (review!=null){
            review.updateVote(false);
            repository.save(review);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found");
        }
    }

    @Override
    public boolean reviewExistence(UUID reviewId){
        Optional<Review> review = Optional.ofNullable(repository.getReviewById(reviewId));
        boolean isPresent= review.isPresent();
        return isPresent;
    }

    @Override
    public List<Review> getReviewsBySandwichOrderByDateWithoutPage(UUID sandwichId) throws IOException, InterruptedException {

        if (helper.doesSandwichExist(sandwichId)) {
            return repository.getReviewsBySandwichOrderByDateWithoutPage(sandwichId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sandwich Not Found");
        }

    }

    @Override
    public List<Review> getReviewsBySandwichOrderByVotesWithoutPage(UUID sandwichId) throws IOException, InterruptedException {

        if (helper.doesSandwichExist(sandwichId)) {
            return repository.getReviewsBySandwichOrderByVotesWithoutPage(sandwichId);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sandwich Not Found");
        }
    }


    @Override
    public List<Review> getAllMyReviews(){
        Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));
        return repository.getAllMyReviews(userId);
    }

    @Override
    public List<Review> getReviewByLanguage(String language) {
        return repository.getReviewByLanguage(language);
    }

    @Override
    public ReviewVoteDTO getReviewForVote(UUID reviewId) {
        Optional<Review> review = Optional.ofNullable(repository.getReviewById(reviewId));
        if (review.isPresent()) {
            ReviewVoteDTO reviewVoteDTO = new ReviewVoteDTO();
            reviewVoteDTO.setReviewId(reviewId);
            reviewVoteDTO.setStatus(review.get().getStatus());
            return reviewVoteDTO;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Review Not Found");
    }
}
