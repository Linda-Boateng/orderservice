package com.example.orderservice.service;

import com.example.orderservice.dto.request.OrderDto;
import com.example.orderservice.dto.request.PaymentDto;
import com.example.orderservice.dto.response.OrderResponseDto;
import com.example.orderservice.dto.response.PaymentResponseDto;
import com.example.orderservice.model.Order;

import java.util.List;

public interface OrderService {
    OrderResponseDto checkOut(OrderDto orderDto);
    PaymentResponseDto orderPayment(PaymentDto paymentDto);

    List<Order> getAllOrders();

    List<Order> getAllOrders(Long userId);
}
