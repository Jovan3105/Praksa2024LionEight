package com.example.demo.controllers;

import com.example.demo.dtos.LoginDto;
import com.example.demo.dtos.RegisterDto;
import com.example.demo.entity.Users;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<Users> findAll() {
        return userService.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Boolean isAuthenticated = userService.login(loginDto.getEmail(), loginDto.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
//        @PostMapping("/checkLogin")
//        public ResponseEntity<Boolean> checkLogin(@RequestBody LoginDto credentials) {
//        String email = credentials.getEmail();
//        String password = credentials.getPassword();
//
//        return ResponseEntity.ok(userService.checkLogin(email,password));
//    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        try {
            boolean isRegistered = userService.register(registerDto);
            if (isRegistered) {
                return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//        @PostMapping("/register")
//        public boolean register(@RequestBody RegisterDto registerDto){
//        return userService.registerUser(registerDto);
//
//    }

}
