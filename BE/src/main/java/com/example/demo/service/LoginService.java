package com.example.demo.service;

import com.example.demo.dtos.RegisterDto;
import com.example.demo.entity.Users;
import java.util.List;

public interface LoginService {
    List<Users> findAll();
    void save(Users userObj);
    boolean login(String email, String password);
    boolean registracija(RegisterDto registerDto);
}
