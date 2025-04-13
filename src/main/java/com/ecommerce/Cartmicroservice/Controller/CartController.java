package com.ecommerce.Cartmicroservice.Controller;

import com.ecommerce.Cartmicroservice.Dto.AddItemRequest;
import com.ecommerce.Cartmicroservice.Dto.RemoveItemRequest;
import com.ecommerce.Cartmicroservice.Service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/item")
    public ResponseEntity<Void>addItem(@RequestBody @Valid AddItemRequest request){
        log.info("Request recibido: {}", request);
        cartService.addItem(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void>removeItem(@PathVariable String productId, @RequestBody @Valid RemoveItemRequest request){
        cartService.removeItem(productId, request);
        return ResponseEntity.ok().build();
    }
}
