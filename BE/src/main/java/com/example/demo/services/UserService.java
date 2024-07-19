package com.example.demo.services;

import com.example.demo.dtos.RegisterDto;
import com.example.demo.entity.Users;
import java.util.List;

public interface UserService {
    List<Users> findAll();
    void save(Users userObj);
    boolean login(String email, String password);
    boolean register(RegisterDto registerDto);
}
