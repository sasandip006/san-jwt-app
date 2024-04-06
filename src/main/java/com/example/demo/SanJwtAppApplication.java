package com.example.demo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.bean.User;
import com.example.demo.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SanJwtAppApplication {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostConstruct
    private void initUser() {
        String passwordEncoder=encoder.encode("sandh");
        List<User> users=Stream.of(new User(101,"sandh","andh",passwordEncoder,"sandh@nets.eu","ROLE_USER,ROLE_ADMIN"),
                new User(102, "kalyani", "andh", passwordEncoder, "sandh@nets.eu", "ROLE_USER,ROLE_ADMIN"),
                new User(103, "shiv", "andh", passwordEncoder, "shiv@gmail.com", "ROLE_USER")).toList();
        repository.saveAll(users);
    }

	public static void main(String[] args) {
		SpringApplication.run(SanJwtAppApplication.class, args);
	}

}
