package com.ecommerce.usermicroservice.infraestructure.persistence.repository;

import com.ecommerce.usermicroservice.infraestructure.persistence.entity.EventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoEventRepository extends MongoRepository<EventDocument, String> {
}
