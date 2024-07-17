package com.example.demo.service;

import com.example.demo.dao.LoginRepository;
import com.example.demo.entity.Users;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public List<Users> findAll() {
        return loginRepository.findAll();
    }

    @Override
    public void save(Users userObj){
        loginRepository.save(userObj);
    }
}
