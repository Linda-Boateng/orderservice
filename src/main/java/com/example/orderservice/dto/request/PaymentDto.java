package com.example.orderservice.dto.request;

import lombok.Data;

@Data
public class PaymentDto {
    private String id;
    private String userId;
    private double totalAmount;
}
