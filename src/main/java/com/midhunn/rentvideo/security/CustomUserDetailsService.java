package com.midhunn.rentvideo.security;

import com.midhunn.rentvideo.models.AppUser;
import com.midhunn.rentvideo.repository.UserRepository;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepository users;

    public CustomUserDetailsService(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        AppUser user = users.findByEmail(
                email.toLowerCase()
        ).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User not found"
                )
        );

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}