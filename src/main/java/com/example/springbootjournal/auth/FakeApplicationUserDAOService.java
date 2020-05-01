package com.example.springbootjournal.auth;

import com.example.springbootjournal.security.ApplicationUserPermission;
import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeApplicationUserDAOService implements ApplicationUserDAO {
    PasswordEncoder passwordEncoder;

    public FakeApplicationUserDAOService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        ArrayList adminAuthorities = new ArrayList<GrantedAuthority>();
        adminAuthorities.add(new SimpleGrantedAuthority(ApplicationUserPermission.JOURNAL_WRITE.getPermission()));
        adminAuthorities.add(new SimpleGrantedAuthority(ApplicationUserPermission.JOURNAL_READ.getPermission()));

        ArrayList traineeAuthorities = new ArrayList<GrantedAuthority>();
        traineeAuthorities.add(new SimpleGrantedAuthority(ApplicationUserPermission.JOURNAL_READ.getPermission()));


        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        adminAuthorities,
                        "admin",
                        passwordEncoder.encode("password"),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        traineeAuthorities,
                        "trainee",
                        passwordEncoder.encode("password"),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }
}
