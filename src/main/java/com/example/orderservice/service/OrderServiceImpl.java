package com.example.orderservice.service;

import com.example.orderservice.dto.request.OrderDto;
import com.example.orderservice.dto.request.PaymentDto;
import com.example.orderservice.dto.response.OrderResponseDto;
import com.example.orderservice.dto.response.PaymentResponseDto;
import com.example.orderservice.exceptions.IncompletePaymentException;
import com.example.orderservice.exceptions.NotFoundException;
import com.example.orderservice.model.Book;
import com.example.orderservice.model.Cart;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.CartRepository;
import com.example.orderservice.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final CartRepository cartRepository;

  /** {@inheritDoc} */
  @Override
  public OrderResponseDto checkOut(OrderDto orderDto) {
    Optional<Cart> existingCart = cartRepository.findByUserId(orderDto.getUserId());
    if (existingCart.isEmpty()) throw new NotFoundException("You have not added to your cart");
    Cart userCart = existingCart.get();
    double totalPrice = calculatePrice(userCart.getBooks());
    Order order =
        Order.builder()
            .userId(userCart.getUserId())
            .books(userCart.getBooks())
            .totalAmount(totalPrice)
            .build();
    Order createdOrder = orderRepository.save(order);
    return OrderResponseDto.builder()
        .message("Checkout completed proceed to payment")
        .order(createdOrder)
        .build();
  }

  /** {@inheritDoc} */
  @Override
  public PaymentResponseDto orderPayment(PaymentDto paymentDto) {
    Optional<Order> existingOrder = orderRepository.findById(paymentDto.getId());
    if (existingOrder.isEmpty()) throw new NotFoundException("Order not found");
    Order userOrder = existingOrder.get();
    double amount = userOrder.getTotalAmount();
    if (paymentDto.getTotalAmount() != amount)
      throw new IncompletePaymentException("Enter full Amount please");
    userOrder.setPaid(true);
    orderRepository.save(userOrder);
    cartRepository.deleteByUserId(paymentDto.getUserId());
    return PaymentResponseDto.builder().message("Payment made successfully").build();
  }

  /** {@inheritDoc} */
  @Override
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

    /** {@inheritDoc} */
  @Override
  public List<Order> getAllOrders(Long userId) {
    return orderRepository.findByUserId(userId);
  }


  /**
   * Calculate the total price of the books in the cart
   *
   * @param books the list of books
   * @return the total price
   */
  private double calculatePrice(List<Book> books) {
    double totalPrice = 0;
    for (Book book : books) {
      totalPrice += book.getPrice();
    }
    return totalPrice;
  }
}
