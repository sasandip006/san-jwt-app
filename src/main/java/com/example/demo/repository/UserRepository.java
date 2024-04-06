package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.User;
import com.example.demo.bean.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByFirstName(String username);

    void save(UserInfo userInfo);

}
