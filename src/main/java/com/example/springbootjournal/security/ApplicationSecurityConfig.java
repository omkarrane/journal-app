package com.example.springbootjournal.security;

import com.example.springbootjournal.auth.ApplicationUserService;
import com.example.springbootjournal.jwt.JWTAuthenticationFilter;
import com.example.springbootjournal.jwt.JWTConfig;
import com.example.springbootjournal.jwt.JWTTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private final SecretKey secretKey;
    private JWTConfig jwtConfig;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     ApplicationUserService applicationUserService, SecretKey secretKey, JWTConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JWTTokenVerifier(secretKey, jwtConfig), JWTAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
