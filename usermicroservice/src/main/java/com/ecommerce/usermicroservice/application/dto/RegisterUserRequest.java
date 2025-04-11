package com.ecommerce.usermicroservice.application.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;

}
