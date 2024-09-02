package com.example.BookStoreApplication.controller;

import com.example.BookStoreApplication.dto.OrderDTO;
import com.example.BookStoreApplication.model.Order;
import com.example.BookStoreApplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Purpose: Place a new order.
     * @param token Authentication token.
     * @param orderDTO Data transfer object containing order details.
     * @return ResponseEntity containing the placed order.
     *
     * Input:
     * - token: The authentication token for the user.
     * - orderDTO: The order details provided by the user.
     *
     * Output:
     * - ResponseEntity<Order>: The placed order wrapped in a ResponseEntity.
     */
    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestHeader String token, @RequestBody OrderDTO orderDTO) {
        return orderService.placeOrder(token, orderDTO);
    }

    /**
     * Purpose: Cancel an existing order.
     * @param token Authentication token.
     * @param orderId ID of the order to be canceled.
     * @return ResponseEntity containing a boolean indicating success or failure.
     *
     * Input:
     * - token: The authentication token for the user.
     * - orderId: The ID of the order to be canceled.
     *
     * Output:
     * - ResponseEntity<Boolean>: A boolean indicating whether the cancellation was successful.
     */
    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<Boolean> cancelOrder(@RequestHeader String token, @PathVariable Long orderId) {
        return orderService.cancelOrder(token, orderId);
    }

    /**
     * Purpose: Get all orders.
     * @param token Authentication token.
     * @return ResponseEntity containing a list of all orders.
     *
     * Input:
     * - token: The authentication token for the user.
     *
     * Output:
     * - ResponseEntity<List<Order>>: A list of all orders wrapped in a ResponseEntity.
     */
    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders(@RequestHeader String token) {
        return orderService.getAllOrders(token);
    }

    /**
     * Purpose: Get all orders for a specific user.
     * @param token Authentication token.
     * @return ResponseEntity containing a list of orders for the user.
     *
     * Input:
     * - token: The authentication token for the user.
     *
     * Output:
     * - ResponseEntity<List<Order>>: A list of orders for the user wrapped in a ResponseEntity.
     */
    @GetMapping("/getAllOrdersForUser")
    public ResponseEntity<List<Order>> getAllOrdersForUser(@RequestHeader String token) {
        return orderService.getAllOrdersForUser(token);
    }

    /**
     * Purpose: Get all canceled orders.
     * @param token Authentication token.
     * @return ResponseEntity containing a list of all canceled orders.
     *
     * Input:
     * - token: The authentication token for the user.
     *
     * Output:
     * - ResponseEntity<List<Order>>: A list of all canceled orders wrapped in a ResponseEntity.
     */
    @GetMapping("/getAllCancelOrders")
    public ResponseEntity<List<Order>> getAllCancelOrders(@RequestHeader String token){
        return orderService.getAllCancelOrders(token);
    }

    /**
     * Purpose: Get all placed orders.
     * @param token Authentication token.
     * @return ResponseEntity containing a list of all placed orders.
     *
     * Input:
     * - token: The authentication token for the user.
     *
     * Output:
     * - ResponseEntity<List<Order>>: A list of all placed orders wrapped in a ResponseEntity.
     */
    @GetMapping("/getAllPlacedOrders")
    public ResponseEntity<List<Order>> getAllPlacedOrders(@RequestHeader String token){
        return orderService.getAllPlacedOrders(token);
    }

    /**
     * Purpose: Get all canceled orders for a specific user.
     * @param token Authentication token.
     * @return ResponseEntity containing a list of canceled orders for the user.
     *
     * Input:
     * - token: The authentication token for the user.
     *
     * Output:
     * - ResponseEntity<List<Order>>: A list of canceled orders for the user wrapped in a ResponseEntity.
     */
    @GetMapping("/getAllCancelOrdersForUser")
    public ResponseEntity<List<Order>> getAllCancelOrdersForUser(@RequestHeader String token){
        return orderService.getAllCancelOrdersForUser(token);
    }

    /**
     * Purpose: Get all placed orders for a specific user.
     * @param token Authentication token.
     * @return ResponseEntity containing a list of placed orders for the user.
     *
     * Input:
     * - token: The authentication token for the user.
     *
     * Output:
     * - ResponseEntity<List<Order>>: A list of placed orders for the user wrapped in a ResponseEntity.
     */
    @GetMapping("/getAllPlacedOrdersForUser")
    public ResponseEntity<List<Order>> getAllPlacedOrdersForUser(@RequestHeader String token){
        return orderService.getAllPlacedOrdersForUser(token);
    }
}
