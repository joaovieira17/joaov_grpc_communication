package com.votespesta.repositories;

import com.votespesta.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
/*
    @Query("SELECT r FROM Review r WHERE r.reviewId = :reviewId")
    Review getReviewById(@Param("reviewId") Long reviewId);

    @Query("SELECT r FROM Review r WHERE r.product.sku = :sku  ORDER BY r.date desc")
    Page <Review> getReviewsByProductOrderByDate(@Param("sku") String sku, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.product.sku = :sku and r.status = 'APPROVED' ORDER BY r.upVotes desc, r.date desc")
    Page <Review> getReviewsByProductOrderByVotes(@Param("sku") String sku, Pageable pageable);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.sku = :sku")
    Float getAggregatedRating(@Param("sku") String sku);

    @Query("SELECT r FROM Review r WHERE r.status ='PENDING'")
    Page <Review> getAllPendingReviews(Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.userId = :userId")
    Page <Review> getAllMyReviews(@Param("userId") Long userId,Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.product.sku =:sku")
    Iterable<Review> getReviewsByProduct(@Param("sku") String sku);
*/
}
