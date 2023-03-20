package com.reviewspesta.services;

import com.reviewspesta.model.*;
//import com.psoftprojectg5.repositories.ProductRepository;
import com.reviewspesta.repositories.HttpReviewRepository;
import com.reviewspesta.repositories.ProductRepository;
import com.reviewspesta.repositories.ReviewRepository;
import com.reviewspesta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;


@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository repository;


    private ProductRepository repository2 = new ProductRepository();

    @Autowired
    private UserRepository userRepository;


    private HttpRequestHelper helper=new HttpRequestHelper();

    private HttpReviewRepository httpRepo= new HttpReviewRepository();


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
    public List<Review> getReviewsByProduct(String sku) throws IOException, InterruptedException {

        if (repository2.isProduct1(sku)) {

            return repository.getReviewsByProduct(sku);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        }

    }

    @Override
    public List<Review> getLocalPendingReviews() {
        List<Review> thisPending= repository.getAllPendingReviews();
        return thisPending;
    }

    @Override
    public Review create(ReviewDTO rev, String sku) throws IOException, InterruptedException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        User user = userRepository.findByUsername(username);


        if (repository2.isProduct1(sku)){
            final Review obj = Review.newFrom(rev,sku,user.getId());

            obj.setProductSku(sku);
            return repository.save(obj);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        }

    }

    @Override
    public RatingFrequency getRatingFrequencyOfProduct(String sku) throws IOException, InterruptedException {
        if(repository2.isProduct1(sku)){
            List<Review> reviews = getReviewsByProduct(sku);
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
            float globalRating =repository.getAggregatedRating(sku);
            return new RatingFrequency(one, two, three, four, five, globalRating);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        }
    }

    @Override
    public boolean approveRejectReview(UUID reviewId, boolean status){

        Review review = repository.getReviewById(reviewId);
        try {
            if (Objects.equals(review.getStatus(), "PENDING")) {
                if (status) {
                    review.setStatus("APPROVED");
                } else {
                    review.setStatus("REJECTED");
                }
                repository.save(review);
                return true;
            }else {
                return false;
            }
        }catch (NullPointerException e){
            return false;
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
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        User user = userRepository.findByUsername(username);

        return Objects.equals(review.getUserId(), user.getId());
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
    public List<Review> getReviewsByProductOrderByDateWithoutPage(String sku) throws IOException, InterruptedException {

        if (repository2.isProduct1(sku)) {
            return repository.getReviewsByProductOrderByDateWithoutPage(sku);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        }

    }

    @Override
    public List<Review> getReviewsByProductOrderByVotesWithoutPage(String sku) throws IOException, InterruptedException {

        if (repository2.isProduct1(sku)) {
            return repository.getReviewsByProductOrderByVotesWithoutPage(sku);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        }
    }


    @Override
    public List<Review> getAllMyLocalReviews(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username);
        return repository.getAllMyReviews(user.getId());
    }
}
