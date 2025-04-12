package com.ecommerce.usermicroservice.infraestructure.persistence.repository;

import com.ecommerce.usermicroservice.infraestructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByEmail(String email);
}
