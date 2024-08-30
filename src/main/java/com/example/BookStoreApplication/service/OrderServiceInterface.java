package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.OrderDTO;
import com.example.BookStoreApplication.model.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderServiceInterface {

    ResponseEntity<Order> placeOrder(String token, OrderDTO orderDTO);

    ResponseEntity<Boolean> cancelOrder(String token, Long orderId);

    ResponseEntity<List<Order>> getAllOrders(String token);

    ResponseEntity<List<Order>> getAllOrdersForUser(String token);

    ResponseEntity<List<Order>> getAllCancelOrders(String token);

    ResponseEntity<List<Order>> getAllPlacedOrders(String token);

    ResponseEntity<List<Order>> getAllCancelOrdersForUser(String token);

    ResponseEntity<List<Order>> getAllPlacedOrdersForUser(String token);

}
