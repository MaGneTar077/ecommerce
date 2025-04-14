package com.api.product.Application.Service;


import com.api.product.Domain.Entity.Product;
import com.api.product.Domain.Event.EventProduct;
import com.api.product.Infrastructure.Repository.EventRepository;
import com.api.product.Infrastructure.Repository.ProductEntity;
import com.api.product.Infrastructure.Repository.ProductMongo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ProductService {
    private final ProductMongo productMongo;
    private final EventRepository eventRepository;

    public ProductService(ProductMongo productMongo, EventRepository eventRepository) {
        this.productMongo = productMongo;
        this.eventRepository = eventRepository;
    }

    public Product createProduct(Product product) {
        ProductEntity entity = new ProductEntity(
                product.getName(), product.getDescription(),
                product.getPrice(), product.getCategory());

        ProductEntity save = productMongo.save(entity);

        Product saveProduct= new Product(
                save.getId(), save.getName(),
                save.getDescription(), save.getPrice(),
                save.getCategory());

        Map<String, Object> payload = Map.of(
                "name", saveProduct.getId(),
                "description", saveProduct.getDescription(),
                "price", saveProduct.getPrice(),
                "category", saveProduct.getCategory()
        );

        Map<String, Object> snapshot = Map.of(
                "productId", saveProduct.getId(),
                "status", "CREATED"
        );

        EventProduct event = EventProduct.builder()
                .eventId("evt_"+UUID.randomUUID().toString())
                .timestamp(LocalDateTime.now())
                .source("ProductService")
                .payload(payload)
                .snapshot(snapshot)
                .build();

        eventRepository.save(event);

        return saveProduct;

    }

    public List<Product> getAllProducts() {
        return productMongo.findAll().stream()
                .map(e -> new Product(e.getId(), e.getName(), e.getDescription(), e.getPrice(), e.getCategory()))
                .collect(Collectors.toList());
    }
}