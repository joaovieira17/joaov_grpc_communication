package com.restpesta.repositories;

import com.restpesta.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    @Query("SELECT r.id FROM User r WHERE r.username = :username")
    Long getIdByUsername(String username);

    Optional <User> findById(Long id);

}
