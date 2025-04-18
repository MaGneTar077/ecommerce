package com.ecommerce.NotificationMicroservice.adapter.out.mongo;

import com.ecommerce.NotificationMicroservice.model.EventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventMongoRepository extends MongoRepository<EventDocument, String> {
}
