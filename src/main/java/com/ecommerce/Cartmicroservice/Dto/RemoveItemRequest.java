package com.ecommerce.Cartmicroservice.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RemoveItemRequest {
    @NotBlank
    private String userId;
}
