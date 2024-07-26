package com.example.demo.controllers;

import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {


    private final UserService userService;


    @GetMapping("/api/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.logout(token));
    }

}
