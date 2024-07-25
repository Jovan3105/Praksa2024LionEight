package com.example.demo.controllers;

import com.example.demo.controllers.reponses.AuthenticationResponse;
import com.example.demo.controllers.requests.LoginRequest;
import com.example.demo.controllers.requests.RegisterRequest;
import com.example.demo.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest request){

        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login (@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}
