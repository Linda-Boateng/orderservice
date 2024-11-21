package com.example.orderservice.controller;

import com.example.orderservice.dto.request.OrderDto;
import com.example.orderservice.dto.request.PaymentDto;
import com.example.orderservice.dto.response.OrderResponseDto;
import com.example.orderservice.dto.response.PaymentResponseDto;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/user/order")
    public ResponseEntity<OrderResponseDto> order(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.checkOut(orderDto), HttpStatus.CREATED);
    }

    @GetMapping("/admin/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping("/user/payment")
    public ResponseEntity<PaymentResponseDto> payment(@RequestBody PaymentDto paymentDto){
        return new ResponseEntity<>(orderService.orderPayment(paymentDto),HttpStatus.OK);
    }

    @GetMapping("/user/orders/{userId}")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getAllOrders(userId), HttpStatus.OK);
    }
}
