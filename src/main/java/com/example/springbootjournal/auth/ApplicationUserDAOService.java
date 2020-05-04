package com.example.springbootjournal.auth;

import java.util.Optional;

public class ApplicationUserDAOService implements ApplicationUserDAO {

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return Optional.empty();
    }
}
