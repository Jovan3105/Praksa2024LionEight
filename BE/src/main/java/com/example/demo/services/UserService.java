package com.example.demo.services;

import com.example.demo.dao.LoginRepository;
import com.example.demo.dtos.RegisterDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.entity.Users;
import com.example.demo.service.LoginService;
import com.example.demo.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<UserDto> users = new ArrayList<>();
    @Autowired
    private LoginRepository loginRepository;
    public UserService(){

        users.add(new UserDto(1L,"Darko","Kovacevic","Dare","sifra123"));
        users.add(new UserDto(1L,"Marko","Ivanovic","Mare","sifra123"));
    }

    public Boolean checkLogin(String email, String password) {
        Optional<Users> user = loginRepository.findAll().stream().filter(users1 -> email.equals(users1.getEmail())).findFirst();;

        if(user.isPresent()) {
            if (user.get().getPassword().equals(password))
                return true;
            return false;
        }
        else
            return false;
    }
    public Boolean registerUser(RegisterDto registerDto){
        Optional<Users> user = loginRepository.findAll().stream().filter(users1 -> registerDto.getEmail().equals(users1.getEmail())).findFirst();
        if(user.isPresent())
            return false;

        Users u = new Users();
        u.setName(registerDto.getName());
        u.setSurname(registerDto.getSurname());
        u.setEmail(registerDto.getEmail());
        u.setPassword(registerDto.getPassword());

        loginRepository.save(u);

        return true;
    }
}
