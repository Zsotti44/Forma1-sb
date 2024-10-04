package com.f1sb.forma1sb.controller;

import com.f1sb.forma1sb.model.UserDTO;
import com.f1sb.forma1sb.service.UserService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final UserService userService;

    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO registrationRequest) {
        // Ellenőrizzük, hogy a felhasználónév vagy a jelszó üres-e
        if (registrationRequest.getName() == null || registrationRequest.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and password are required.");
        }

        // Regisztráció
        int result = userService.registration(registrationRequest.getName(), registrationRequest.getPassword());

        // Ellenőrizés
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } else {
            return ResponseEntity.status(400).body("User registration failed.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader("Authorization") String authHeader) {
        //Header Authorization-ben kapott érték ellenőrzése
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }
        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);

        final String[] values = credentials.split(":", 2);
        if (values.length < 2) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String username = values[0];
        String password = values[1];

        UserDTO user = userService.verifyPassword(username, password);

        if (user == null) { //Sikertelen a hitelesítés
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        } else { //Sikeres hitelesítés
            Map<String, Object> response = new HashMap<>();
            response.put("userId", user.getId());
            response.put("userName", user.getName());
            response.put("permission", user.getPermission());
            JSONObject jsonObject = new JSONObject(response);
            return ResponseEntity.ok(jsonObject.toString());
        }
    }
}
