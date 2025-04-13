package com.api.product.Infrastructure.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongo extends MongoRepository<ProductEntity, String> {

}
