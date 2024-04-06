package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @JsonProperty(value = "f_name")
    private String firstName;
    @JsonProperty(value = "l_name")
    private String lastName;
    @JsonProperty(value = "mail")
    private String email;
    @JsonProperty(value = "roles")
    private String roles;

    private String password;

}
