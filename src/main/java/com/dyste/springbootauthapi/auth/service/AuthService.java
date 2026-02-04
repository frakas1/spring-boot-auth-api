package com.dyste.springbootauthapi.auth.service;

import com.dyste.springbootauthapi.auth.model.User;
import com.dyste.springbootauthapi.auth.repository.UserRepository;
import com.dyste.springbootauthapi.auth.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
            throw new RuntimeException("Username already taken");
        }
        String hash = encoder.encode(password);
        userRepo.save(new User(username, hash));
    }

    public String login(String username, String password) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(username);
    }
}
