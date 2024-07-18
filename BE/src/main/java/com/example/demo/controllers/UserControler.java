package com.example.demo.controllers;

import com.example.demo.dtos.CredentialsDto;
import com.example.demo.dtos.LoginDto;
import com.example.demo.dtos.RegisterDto;
import com.example.demo.entity.Users;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class UserControler {
    private  UserService userService;

    @PostMapping("/checkLogin")
    public ResponseEntity<Boolean> checkLogin(@RequestBody LoginDto credentials) {
        String email = credentials.getEmail();
        String password = credentials.getPassword();

        return ResponseEntity.ok(userService.checkLogin(email,password));
    }


    @PostMapping("/register")
    public boolean register(@RequestBody RegisterDto registerDto){
        return userService.registerUser(registerDto);

    }
}



