package com.api.product.Aplication.Service;


import com.api.product.Domain.Entity.Product;
import com.api.product.Infrastructure.Repository.ProductEntity;
import com.api.product.Infrastructure.Repository.ProductMongo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {
    private final ProductMongo productMongo;

    public ProductService(ProductMongo productMongo){
        this.productMongo= productMongo;
    }

    public Product createProduct(Product product){
        ProductEntity entity= new ProductEntity(
                product.getName(), product.getDescription(),
                product.getPrice(), product.getCategory());

        ProductEntity save= productMongo.save(entity);
        return new Product(
                save.getId(), save.getName(),
                save.getDescription(), save.getPrice(),
                save.getCategory());
    }

    public List<Product> getAllProducts(){
        return productMongo.findAll().stream()
                .map(e -> new Product(e.getId(), e.getName(), e.getDescription(), e.getPrice(), e.getCategory()))
                .collect(Collectors.toList());
    }

}
