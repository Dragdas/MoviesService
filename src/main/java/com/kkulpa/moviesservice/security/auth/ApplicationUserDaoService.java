package com.kkulpa.moviesservice.security.auth;

import com.kkulpa.moviesservice.backend.domain.User;
import com.kkulpa.moviesservice.backend.repositories.UserRepository;
import com.kkulpa.moviesservice.security.ApplicationUserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ApplicationUserDaoService implements ApplicationUserDao{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {

        Optional<User> user = userRepository.findByUserName(username);

        if (user.isEmpty())
            return Optional.empty();

        User userPresent = user.get();

        return Optional.of(new ApplicationUser(
                userPresent.getUserName(),
                passwordEncoder.encode(userPresent.getPassword()),
                ApplicationUserRole.USER.getGrantedAuthorities(),
                true,
                true,
                true,
                true
        ));
    }

}
