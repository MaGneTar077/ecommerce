package com.ecommerce.usermicroservice.application.usecase;

import com.ecommerce.usermicroservice.application.dto.RegisterUserRequest;

public interface RegisterUserUseCase {

    void register(RegisterUserRequest request);

}
