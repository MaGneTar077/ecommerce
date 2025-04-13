package com.ecommerce.Cartmicroservice.Controller;

import com.ecommerce.Cartmicroservice.Dto.AddItemRequest;
import com.ecommerce.Cartmicroservice.Dto.RemoveItemRequest;
import com.ecommerce.Cartmicroservice.Service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/items")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Void>addItem(@RequestBody @Valid AddItemRequest request){
        cartService.addItem(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void>removeItem(@PathVariable String productId, @RequestBody @Valid RemoveItemRequest request){
        cartService.removeItem(productId, request);
        return ResponseEntity.ok().build();
    }
}
