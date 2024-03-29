package com.votespesta.repositories;

import com.votespesta.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Integer> {
    @Query ("SELECT v FROM Vote v WHERE v.userId = :userId AND v.reviewId = :reviewId")
    Vote findByUserAndId(@Param("userId")Long userId,@Param("reviewId") UUID reviewId);

    @Query ("SELECT v FROM Vote v WHERE v.userId = :userId")
    List<Vote> getAllMyVotes(@Param("userId")Long userId);
}
