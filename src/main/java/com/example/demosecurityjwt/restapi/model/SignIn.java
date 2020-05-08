package com.example.demosecurityjwt.restapi.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SignIn {
    @NotEmpty(message = "User Name/Email Id is required")
    private String userNameOrEmailId;
    @NotEmpty(message = "Password is required")
    private String password;
}
