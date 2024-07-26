package com.example.demo.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;


public interface UserService {
    String logout(@RequestHeader("Authorization") String token);
}
