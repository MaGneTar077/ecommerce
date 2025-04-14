package com.api.product.Infrastructure.Repository;

import com.api.product.Domain.Event.EventProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<EventProduct, String> {

}
