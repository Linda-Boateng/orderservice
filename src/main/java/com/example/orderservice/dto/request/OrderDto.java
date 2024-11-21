package com.example.orderservice.dto.request;

import lombok.Data;

@Data
public class OrderDto {
    private String productId;
    private int quantity;
    private String userId;

}
