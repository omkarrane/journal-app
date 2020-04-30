package com.example.springbootjournal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/all").hasAuthority(ApplicationUserPermission.JOURNAL_READ.getPermission())
//                .antMatchers(HttpMethod.POST, "/add").hasAuthority(ApplicationUserPermission.JOURNAL_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        ArrayList adminAuthorities = new ArrayList<GrantedAuthority>();
        adminAuthorities.add(new SimpleGrantedAuthority(ApplicationUserPermission.JOURNAL_READ.getPermission()));
        adminAuthorities.add(new SimpleGrantedAuthority(ApplicationUserPermission.JOURNAL_WRITE.getPermission()));

        ArrayList traineeAuthorities = new ArrayList<GrantedAuthority>();
        traineeAuthorities.add(new SimpleGrantedAuthority(ApplicationUserPermission.JOURNAL_READ.getPermission()));

        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities(adminAuthorities)
                .build();

        UserDetails traineeUser = User.builder()
                .username("trainee")
                .password(passwordEncoder.encode("trainee"))
                .authorities(traineeAuthorities)
                .build();

        return new InMemoryUserDetailsManager(
                adminUser,
                traineeUser
        );
    }
}
