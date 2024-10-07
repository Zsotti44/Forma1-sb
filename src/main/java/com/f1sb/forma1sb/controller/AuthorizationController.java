package com.f1sb.forma1sb.controller;

import com.f1sb.forma1sb.model.CustomUserDetails;
import com.f1sb.forma1sb.service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final MyUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthorizationController(MyUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody CustomUserDetails registrationRequest) {
        // Ellenőrizzük, hogy a felhasználónév vagy a jelszó üres-e
        if (registrationRequest.getUsername() == null || registrationRequest.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and password are required.");
        }

        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());

        // Regisztráció
        int result = userDetailsService.registration(registrationRequest.getUsername(), encodedPassword);

        // Ellenőrizés
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } else {
            return ResponseEntity.status(400).body("User registration failed.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader("Authorization") String authHeader) {
        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);

        String username = credentials.split(":", 2)[0];
        String password = credentials.split(":", 2)[1];

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            authenticationManager.authenticate(authenticationToken);

            return ResponseEntity.ok("Logged in successfully.");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok("Logout successful");
    }
}
