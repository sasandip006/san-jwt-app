package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class ControllerAuthentication {


    @GetMapping("/welm")
    public String getWlcomeMsg() {
        return "Welcome User, Pleasure to help you here.";
    }


    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getUserMessage() {
        return "welcome You got User Access and Authorities for this application";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getAdminMessage() {
        return "Welcome You are logged in as a ADMIN , YOu will got the full access for this application";
    }


}
