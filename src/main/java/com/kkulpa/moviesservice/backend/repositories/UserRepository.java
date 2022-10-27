package com.kkulpa.moviesservice.backend.repositories;

import com.kkulpa.moviesservice.backend.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByUserName(String userName);

    @Query(value = "select u from User u " +
                    "LEFT JOIN FETCH u.userDetails " +
                    "LEFT JOIN FETCH u.ratings " +
                    "WHERE u.userName LIKE :username")
    Optional<User> findByUserName(@Param("username") String username);



}
