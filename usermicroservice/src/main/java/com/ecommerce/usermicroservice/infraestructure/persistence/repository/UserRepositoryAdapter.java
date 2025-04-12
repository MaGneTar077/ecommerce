package com.ecommerce.usermicroservice.infraestructure.persistence.repository;

import com.ecommerce.usermicroservice.domain.model.User;
import com.ecommerce.usermicroservice.domain.repository.UserRepository;
import com.ecommerce.usermicroservice.infraestructure.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        var entity = UserMapper.toEntity(user);
        var saved = jpaUserRepository.save(entity);
        return UserMapper.toDomain(saved);
    }

    @Override
    public boolean existByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }
}
