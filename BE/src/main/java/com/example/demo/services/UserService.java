package com.example.demo.services;

import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface UserService {
    public String logout(@RequestHeader("Authorization") String token);
}
