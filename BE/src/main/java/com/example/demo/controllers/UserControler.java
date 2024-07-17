package com.example.demo.controllers;

import com.example.demo.dtos.CredentialsDto;
import com.example.demo.dtos.LoginDto;
import com.example.demo.dtos.RegisterDto;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class UserControler {
    private final UserService userService;

    @PostMapping("/checkLogin")
    public Boolean checkLogin(@RequestBody LoginDto credentials) {
        String userName = credentials.getUsername();
        String password = credentials.getPassword();

        return userService.checkLogin(userName, password);
    }

    @PostMapping("/register")
    public boolean register(@RequestBody RegisterDto registerDto){
        return userService.registerUser(registerDto);

    }
}



