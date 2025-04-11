package com.ecommerce.usermicroservice.domain.repository;

import com.ecommerce.usermicroservice.domain.model.UserEvent;

public interface EventStoreRepository {

    void save(UserEvent event);

}
