package com.dyste.springbootauthapi.auth.controller;

import com.dyste.springbootauthapi.auth.dto.AuthResponse;
import com.dyste.springbootauthapi.auth.dto.LoginRequest;
import com.dyste.springbootauthapi.auth.dto.RegisterRequest;
import com.dyste.springbootauthapi.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest req) {
        authService.register(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(Map.of("message", "registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest req) {
        String token = authService.login(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication auth) {
        String username = (String) auth.getPrincipal();
        return ResponseEntity.ok(Map.of("username", username));
    }
}

