package com.f1sb.forma1sb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diag")
public class DiagController {

    @GetMapping("/available")
    @ResponseBody
    public String sayHello() {
        return "<h1>Service is available</h1>";
    }

    @GetMapping("/sqlHealth")
    public ResponseEntity<String> SqlHealth() {
        return ResponseEntity.ok("Sql is available");
    }
}