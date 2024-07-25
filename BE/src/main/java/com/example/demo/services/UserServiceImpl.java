package com.example.demo.services;


import com.example.demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final TokenStoreService tokenStoreService;
    private final JwtService jwtService;
    @Override
    public String logout(String token) {
        token = token.substring(7);
        String userEmail = jwtService.extractUsername(token);
        tokenStoreService.removeToken(userEmail);
        return "Logout successful";
    }
}
