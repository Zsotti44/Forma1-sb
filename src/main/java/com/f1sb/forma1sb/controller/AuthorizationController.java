package com.f1sb.forma1sb.controller;

import com.f1sb.forma1sb.model.CustomUserDetails;
import com.f1sb.forma1sb.service.MyUserDetailsService;
import org.apache.catalina.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final MyUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    public AuthorizationController(MyUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/info")
    public ResponseEntity<String> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            return ResponseEntity.ok("User: " + username + ", Authorities: " + authorities);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @PostMapping("/registration")
    public RedirectView registerUser(@ModelAttribute CustomUserDetails registrationRequest) {
        // Ellenőrizzük, hogy a felhasználónév vagy a jelszó üres-e
        if (registrationRequest.getUsername() == null || registrationRequest.getPassword() == null) {
            return new RedirectView("/register?error=1");
        }

        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());

        // Regisztráció
        int result = userDetailsService.registration(registrationRequest.getUsername(), encodedPassword);

        // Ellenőrizés
        if (result > 0) {
            return new RedirectView("/login");
        } else {
            return new RedirectView("/register?error=2");
        }
    }

}
