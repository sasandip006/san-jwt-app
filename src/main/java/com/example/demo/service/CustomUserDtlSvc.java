package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.bean.CustomUserDetail;
import com.example.demo.bean.User;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDtlSvc implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByFirstName(username);
        return user.map(CustomUserDetail::new).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User:  %s Not Found in OUr Registry", username)));
    }
}
