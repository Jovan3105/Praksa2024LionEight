package com.example.demo.services;

import com.example.demo.dtos.RegisterDto;
import com.example.demo.dtos.UserDto;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private List<UserDto> users = new ArrayList<>();
    public UserService(){

        users.add(new UserDto(1L,"Darko","Kovacevic","Dare","sifra123"));
        users.add(new UserDto(1L,"Marko","Ivanovic","Mare","sifra123"));
    }

    public Boolean checkLogin(String username, String password) {
        return users.stream()
                .anyMatch(user -> username.equals(user.getUsername()) && password.equals(user.getPassword()));
    }
    public Boolean registerUser(RegisterDto registerDto){
        UserDto u = new UserDto(1L,registerDto.getName(),registerDto.getLastName(),registerDto.getUsername(),registerDto.getPassword());
        users.add(u);
        return true;
    }
}
