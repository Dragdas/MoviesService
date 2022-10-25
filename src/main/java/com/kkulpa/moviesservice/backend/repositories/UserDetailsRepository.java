package com.kkulpa.moviesservice.backend.repositories;

import com.kkulpa.moviesservice.backend.domain.UserDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {

    boolean existsByDisplayName(String displayName);

    @Query(value = "Select u.userDetails FROM User u WHERE u.userName like :username")
    Optional<UserDetails> findByUsername(@Param("username")String username);

}
