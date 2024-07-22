package com.example.demo.controllers;

import com.example.demo.controllers.param.AuthRequest;
import com.example.demo.controllers.response.AuthResponse;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.CustomUserDetailsService;
import com.example.demo.services.TokenStoreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private CustomUserDetailsService userDetailsService;
    private TokenStoreService tokenStroreService;

    @PostMapping("/login")
    public AuthResponse createToken(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        String jwtToken = jwtUtil.generateToken(userDetails);

        tokenStroreService.storeToken(authRequest.getEmail(),jwtToken);
        return new AuthResponse(jwtToken);
    }
}
