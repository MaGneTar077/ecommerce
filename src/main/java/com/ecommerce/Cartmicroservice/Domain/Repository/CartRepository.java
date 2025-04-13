package com.ecommerce.Cartmicroservice.Domain.Repository;

import com.ecommerce.Cartmicroservice.Domain.Model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<CartItem, String>{
    Optional<CartItem> findByUserIdAndProductId(String userId, String productId);
    List<CartItem> findByUserId(String userId);
    void deleteByUserIdAndProductId(String userId,String productId);
}
