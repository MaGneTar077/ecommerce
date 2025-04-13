package com.ecommerce.Cartmicroservice.Domain.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "Cart")
public class CartItem {
    @Id
    private String id;
    private String userId;
    private String productId;
    private int quantity;
    private LocalDateTime updateAt;

}
