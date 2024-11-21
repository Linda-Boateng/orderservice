package com.example.orderservice.dto.response;

import com.example.orderservice.model.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDto {
    private String message;
    private Order order;
}
