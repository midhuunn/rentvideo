package com.midhunn.rentvideo.service;

import com.midhunn.rentvideo.dto.*;
import com.midhunn.rentvideo.models.*;
import com.midhunn.rentvideo.repository.UserRepository;
import com.midhunn.rentvideo.security.JwtService;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;
    private final JwtService jwt;

    public AuthService(
            UserRepository users,
            PasswordEncoder encoder,
            AuthenticationManager manager,
            JwtService jwt) {

        this.users = users;
        this.encoder = encoder;
        this.manager = manager;
        this.jwt = jwt;
    }

    public void register(RegisterRequest request) {

        String email = request.email().toLowerCase();

        if (users.existsByEmail(email)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already registered"
            );
        }

        AppUser user = new AppUser();

        user.setEmail(email);

        // Hash password before saving.
        user.setPassword(
                encoder.encode(request.password())
        );

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());

        user.setRole(
                request.role() == null
                        ? Role.CUSTOMER
                        : request.role()
        );

        users.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        try {

            var authentication =
                    manager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.email().toLowerCase(),
                                    request.password()
                            )
                    );

            UserDetails user =
                    (UserDetails) authentication.getPrincipal();

            String token = jwt.generateToken(user);

            return new AuthResponse(
                    token,
                    "Bearer",
                    jwt.getExpiration()
            );

        } catch (AuthenticationException e) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password"
            );
        }
    }
}