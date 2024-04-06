package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository repository;

    public String addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "User Added Successfully to the application Repository";

    }

}
