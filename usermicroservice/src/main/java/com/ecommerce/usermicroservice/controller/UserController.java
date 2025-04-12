package com.ecommerce.usermicroservice.controller;

import com.ecommerce.usermicroservice.application.dto.RegisterUserRequest;
import com.ecommerce.usermicroservice.application.usecase.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<String>  registerUser(@RequestBody RegisterUserRequest request) {
        registerUserUseCase.register(request);
        return new ResponseEntity<>("User registered sucesfully", HttpStatus.CREATED);
    }

}
