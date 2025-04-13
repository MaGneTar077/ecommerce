package com.ecommerce.Cartmicroservice.Domain.Repository;

import com.ecommerce.Cartmicroservice.Domain.Model.Eventlog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventlogRepository extends MongoRepository<Eventlog, String> {


}
