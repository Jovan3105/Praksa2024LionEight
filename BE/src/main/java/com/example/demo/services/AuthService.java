package com.example.demo.services;

import com.example.demo.controllers.reponses.AuthenticationResponse;
import com.example.demo.controllers.requests.LoginRequest;
import com.example.demo.controllers.requests.RegisterRequest;
import com.example.demo.dao.UserRepository;
import com.example.demo.dtos.Role;
import com.example.demo.entity.Users;
import com.example.demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenStoreService tokenStoreService;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = Users.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        tokenStoreService.storeToken(request.getEmail(),jwtToken);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        tokenStoreService.storeToken(request.getEmail(),jwtToken);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

}
