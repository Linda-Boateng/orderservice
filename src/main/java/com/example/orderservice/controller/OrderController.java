package com.example.orderservice.controller;

import com.example.orderservice.dto.request.OrderDto;
import com.example.orderservice.dto.request.PaymentDto;
import com.example.orderservice.dto.response.OrderResponseDto;
import com.example.orderservice.dto.response.PaymentResponseDto;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(
            summary = "Order",
            description = "Order a book",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Book ordered successfully"),
            })
    @PostMapping("/user/order")
    public ResponseEntity<OrderResponseDto> order(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.checkOut(orderDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all orders",
            description = "Get all orders in the system",
            security = @SecurityRequirement(name = "JWT Bearer Token"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "A list of all orders in the system"),
            })
    @GetMapping("/admin/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @Operation(
            summary = "Payment",
            description = "Payment for an order",
            security = @SecurityRequirement(name = "JWT Bearer Token"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment successful"),
            })
    @PostMapping("/user/payment")
    public ResponseEntity<PaymentResponseDto> payment(@RequestBody PaymentDto paymentDto){
        return new ResponseEntity<>(orderService.orderPayment(paymentDto),HttpStatus.OK);
    }

    @Operation(
            summary = "Get all orders",
            description = "Get all user orders in the system",
            security = @SecurityRequirement(name = "JWT Bearer Token"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "A list of all orders in the system"),
            })
    @GetMapping("/user/orders/{userId}")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getAllOrders(userId), HttpStatus.OK);
    }
}
