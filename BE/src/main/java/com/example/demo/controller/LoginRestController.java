package com.example.demo.controller;

import com.example.demo.dtos.LoginDto;
import com.example.demo.dtos.RegisterDto;
import com.example.demo.entity.Users;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/users")
    List<Users> findAll() {
        return loginService.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Boolean isAuthenticated = loginService.login(loginDto.getEmail(), loginDto.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/registracija")
    public ResponseEntity<String> registracija(@RequestBody RegisterDto registerDto) {
        try {
            boolean isRegistered = loginService.registracija(registerDto);
            if (isRegistered) {
                return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
