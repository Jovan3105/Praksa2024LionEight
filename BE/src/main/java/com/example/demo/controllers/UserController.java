package com.example.demo.controllers;

import com.example.demo.security.JwtService;
import com.example.demo.services.TokenStoreService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {


    private final UserService userService;


    @GetMapping("/api/logout")
    public String logout(@RequestHeader("Authorization") String token){
        return userService.logout(token);
    }

}
