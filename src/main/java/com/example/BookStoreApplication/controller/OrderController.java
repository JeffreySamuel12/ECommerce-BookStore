package com.example.BookStoreApplication.controller;

import com.example.BookStoreApplication.dto.OrderDTO;
import com.example.BookStoreApplication.model.Order;
import com.example.BookStoreApplication.service.OrderService;
import com.example.BookStoreApplication.service.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderServiceInterface orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestHeader String token, @RequestBody OrderDTO orderDTO) {
        return orderService.placeOrder(token, orderDTO);
    }

    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<Boolean> cancelOrder(@RequestHeader String token, @PathVariable Long orderId) {
        return orderService.cancelOrder(token, orderId);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders(@RequestHeader String token) {
        return orderService.getAllOrders(token);
    }

    @GetMapping("/getAllOrdersForUser")
    public ResponseEntity<List<Order>> getAllOrdersForUser(@RequestHeader String token) {
        return orderService.getAllOrdersForUser(token);
    }

    @GetMapping("/getAllCancelOrders")
    public ResponseEntity<List<Order>> getAllCancelOrders(@RequestHeader String token){
        return orderService.getAllCancelOrders(token);
    }

    @GetMapping("/getAllPlacedOrders")
    public ResponseEntity<List<Order>> getAllPlacedOrders(@RequestHeader String token){
        return orderService.getAllPlacedOrders(token);
    }

    @GetMapping("/getAllCancelOrdersForUser")
    public ResponseEntity<List<Order>> getAllCancelOrdersForUser(@RequestHeader String token){
        return orderService.getAllCancelOrdersForUser(token);
    }

    @GetMapping("/getAllPlacedOrdersForUser")
    public ResponseEntity<List<Order>> getAllPlacedOrdersForUser(@RequestHeader String token){
        return orderService.getAllPlacedOrdersForUser(token);
    }
}
