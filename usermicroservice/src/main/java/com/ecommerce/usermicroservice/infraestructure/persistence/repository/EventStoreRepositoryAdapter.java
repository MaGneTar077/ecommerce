package com.ecommerce.usermicroservice.infraestructure.persistence.repository;

import com.ecommerce.usermicroservice.domain.model.UserEvent;
import com.ecommerce.usermicroservice.domain.repository.EventStoreRepository;
import com.ecommerce.usermicroservice.infraestructure.persistence.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EventStoreRepositoryAdapter implements EventStoreRepository {

    private final MongoEventRepository mongoEventRepository;

    @Override
    public void save(UserEvent event) {
        mongoEventRepository.save(EventMapper.toDocument(event));
    }
}
