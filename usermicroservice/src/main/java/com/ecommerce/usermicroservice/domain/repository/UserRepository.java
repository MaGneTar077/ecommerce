package com.ecommerce.usermicroservice.domain.repository;

import com.ecommerce.usermicroservice.domain.model.User;

public interface UserRepository {

    User save(User user);
    boolean existByEmail(String email);

}
