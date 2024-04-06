package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonInclude
public class AuthToken {

    @JsonProperty(value = "name")
    private String userName;
    @JsonProperty(value = "passwd")
    @JsonInclude(content = Include.NON_NULL, value = Include.NON_EMPTY)
    private String password;

}
