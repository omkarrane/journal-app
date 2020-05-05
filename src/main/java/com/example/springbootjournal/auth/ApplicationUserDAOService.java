package com.example.springbootjournal.auth;

import com.example.springbootjournal.domain.User;
import com.example.springbootjournal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("original")
public class ApplicationUserDAOService implements ApplicationUserDAO {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        ArrayList authorities = new ArrayList<GrantedAuthority>();
        user.get().getAuthorities()
                .stream()
                .forEach(authority ->authorities.add(authority));

        if (user.isPresent()) {
            ApplicationUser applicationUser = new ApplicationUser(
                    authorities,
                    user.get().getUsername(),
                    user.get().getPassword(),
                    true,
                    true,
                    true,
                    user.get().getActive()
            );
            return Optional.ofNullable(applicationUser);
        }

        return Optional.empty();
    }
}
