package com.kkulpa.moviesservice.backend.repositories;

import com.kkulpa.moviesservice.backend.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String username);


}
