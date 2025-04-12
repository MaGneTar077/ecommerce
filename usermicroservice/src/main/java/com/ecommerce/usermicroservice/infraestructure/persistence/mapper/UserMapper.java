package com.ecommerce.usermicroservice.infraestructure.persistence.mapper;

import com.ecommerce.usermicroservice.domain.model.User;
import com.ecommerce.usermicroservice.infraestructure.persistence.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User user) {

        UserEntity entity = new UserEntity();

        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setPhone(user.getPhone());

        return entity;
    }

    public static User toDomain(UserEntity entity) {

        return new User(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getPhone()
        );

    }

}
