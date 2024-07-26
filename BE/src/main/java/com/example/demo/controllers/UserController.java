package com.example.demo.controllers;

import com.example.demo.controllers.reponses.LogoutResponse;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {


    private final UserService userService;


    @GetMapping("/api/logout")
    public ResponseEntity<LogoutResponse> logout(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(new LogoutResponse(userService.logout(token)));
    }

}
