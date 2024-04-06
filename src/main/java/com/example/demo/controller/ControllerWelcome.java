package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.AuthToken;
import com.example.demo.bean.User;
import com.example.demo.bean.UserInfo;
import com.example.demo.service.JWTService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/home")
public class ControllerWelcome {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService customUserDtlSvc;

    @Autowired
    JWTService jwtService;

    @GetMapping(value = "/greet/message")
    public String greetMessageFromApp() {
        return "Hello User, Welcome to this application. XXXX";
    }

    @PostMapping("/generateToken")
    // Authority : @PreAuthorize("hasAuthority('EDIT_BOOK')")
//    Role: @PreAuthorize("hasRole('BOOK_ADMIN')")  
    // disable csrf() for accessing post request without authentication
    public String generateToken(@RequestBody AuthToken authToken) {
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authToken.getUserName(), authToken.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authToken.getUserName());
        } else {
            throw new UsernameNotFoundException(
                    "Given USername Is not Found in Our Registry " + authToken.getUserName());
        }
    }
        
        @PostMapping("/addUser")
        public String addNewUser(@RequestBody UserInfo userInfo) {
            return customUserDtlSvc.addUser(
                    new User(0, userInfo.getFirstName(), userInfo.getPassword(), userInfo.getLastName(),
                            userInfo.getEmail(),
                            userInfo.getRoles()));
        }


}
