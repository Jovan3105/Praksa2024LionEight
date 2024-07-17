package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginRestController {

    @Autowired
private LoginService loginService;

    @GetMapping("/users")
    List<Users> findAll() {
        return loginService.findAll();
    }
}
