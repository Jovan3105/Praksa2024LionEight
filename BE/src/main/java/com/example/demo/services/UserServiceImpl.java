package com.example.demo.services;

import com.example.demo.dao.LoginRepository;
import com.example.demo.dtos.RegisterDto;
import com.example.demo.entity.Users;
import com.example.demo.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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

    public boolean login(String email, String password) {
        try {
            List<Users> users = loginRepository.findAll();

            for (Users user : users) {
                if (user.getEmail().equals(email)) {
                    if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                        return true;
                    } else {
                        throw new CustomException("Wrong Password", HttpStatus.NOT_FOUND);
                    }
                }
            }

            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        } catch (CustomException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

//    public Boolean checkLogin(String email, String password) {
//        try {
//            Optional<Users> user = loginRepository.findAll().stream().filter(users1 -> email.equals(users1.getEmail())).findFirst();
//
//            if(user.isPresent()) {
//                if (user.get().getPassword().equals(password)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            } else {
//                throw new CustomException("User not found", HttpStatus.NOT_FOUND);
//            }
//        } catch (CustomException e) {
//            System.err.println(e.getMessage());
//            return false;
//        }
//    }

    @Override
    public boolean register(RegisterDto registerDto) {
        try{
            List<Users> users = loginRepository.findAll();
            for (Users user : users) {
                if (user.getEmail().equals(registerDto.getEmail())) {
                    return false;
                }
            }

            Users u = new Users();
            u.setFirstName(registerDto.getName());
            u.setLastName(registerDto.getSurname());
            u.setEmail(registerDto.getEmail());
            u.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));

            loginRepository.save(u);

            return true;
        } catch (CustomException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

//    public Boolean registerUser(RegisterDto registerDto) {
//        try {
//            Optional<Users> user = loginRepository.findAll().stream()
//                    .filter(users1 -> registerDto.getEmail().equals(users1.getEmail()))
//                    .findFirst();
//
//            if (user.isPresent()) {
//                throw new CustomException("User already exists", HttpStatus.CONFLICT);
//            }
// }
//
//            Users u = new Users();
//            u.setName(registerDto.getName());
//            u.setSurname(registerDto.getSurname());
//            u.setEmail(registerDto.getEmail());
//            u.setPassword(registerDto.getPassword());
//
//            loginRepository.save(u);
//
//            return true;
//        } catch (CustomException e) {
//            System.err.println(e.getMessage());
//            return false;
//        }
//    }
}
