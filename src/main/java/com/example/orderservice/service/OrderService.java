package com.example.orderservice.service;

import com.example.orderservice.dto.request.OrderDto;
import com.example.orderservice.dto.request.PaymentDto;
import com.example.orderservice.dto.response.OrderResponseDto;
import com.example.orderservice.dto.response.PaymentResponseDto;
import com.example.orderservice.model.Order;
import java.util.List;

public interface OrderService {
  /**
   * Checkout an order
   *
   * @param orderDto the order details
   * @return the order response
   */
  OrderResponseDto checkOut(OrderDto orderDto);

    /**
     * Make payment for an order
     *
     * @param paymentDto the payment details
     * @return the payment response
     */
  PaymentResponseDto orderPayment(PaymentDto paymentDto);

  /**
   * Get all orders
   *
   * @return the list of orders
   */
  List<Order> getAllOrders();

    /**
     * Get all orders by user
     *
     * @param userId the user id
     * @return the list of orders
     */
  List<Order> getAllOrders(Long userId);
}
