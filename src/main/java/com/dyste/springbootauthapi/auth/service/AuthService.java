package com.dyste.springbootauthapi.auth.service;

import com.dyste.springbootauthapi.auth.model.User;
import com.dyste.springbootauthapi.auth.repository.UserRepository;
import com.dyste.springbootauthapi.auth.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.dyste.springbootauthapi.exception.ApiException;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    public void register(String username, String password) {
        if (userRepo.existsByUsername(username)) {
            throw new ApiException("Username already taken", 409);
        }
        String hash = encoder.encode(password);
        userRepo.save(new User(username, hash));
    }

    public String login(String username, String password) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ApiException("Invalid credentials", 401));

        if (!encoder.matches(password, user.getPasswordHash())) {
            throw new ApiException("Invalid credentials", 401);
        }

        return jwtUtil.generateToken(username);
    }
}
