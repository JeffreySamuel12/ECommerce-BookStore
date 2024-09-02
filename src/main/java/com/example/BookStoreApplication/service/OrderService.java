package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.DataHolder;
import com.example.BookStoreApplication.dto.OrderDTO;
import com.example.BookStoreApplication.model.Address;
import com.example.BookStoreApplication.model.Book;
import com.example.BookStoreApplication.model.Cart;
import com.example.BookStoreApplication.model.Order;
import com.example.BookStoreApplication.repository.AddressRepository;
import com.example.BookStoreApplication.repository.BookRepository;
import com.example.BookStoreApplication.repository.CartRepository;
import com.example.BookStoreApplication.repository.OrderRepository;
import com.example.BookStoreApplication.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService implements OrderServiceInterface{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TokenUtility tokenUtility;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public ResponseEntity<Order> placeOrder(String token, OrderDTO orderDTO) {
        Long userId = tokenUtility.decode(token).getId();
        System.out.println(userId);
        System.out.println(orderDTO.getBookId());
        Cart cart = cartRepository.findByUserIdAndBookId(userId, orderDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Book book = cart.getBook();
        if (book.getBookQuantity() < cart.getQuantity()) {
            throw new RuntimeException("Insufficient quantity available");
        }

        Address address = orderDTO.getAddress();
        addressRepository.save(address);

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setPrice(cart.getTotalPrice());
        order.setQuantity(cart.getQuantity());
        order.setAddress(address);
        order.setUser(cart.getUser());
        order.setBook(book);
        order.setCancel(false);
        order.setShipmentStatus("placed");

        Order savedOrder = orderRepository.save(order);

        book.setBookQuantity(book.getBookQuantity() - cart.getQuantity());
        bookRepository.save(book);

        cartRepository.delete(cart);

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Boolean> cancelOrder(String token, Long orderId) {
        Long userId = tokenUtility.decode(token).getId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!userId.equals(order.getUser().getId())) {
            throw new RuntimeException("User not authorized to cancel this order");
        }

        if (!order.getShipmentStatus().equalsIgnoreCase("placed")) {
            throw new RuntimeException("Order cannot be cancelled as it is already " + order.getShipmentStatus());
        }

        if (order.getShipmentStatus().equalsIgnoreCase("delivered")) {
            throw new RuntimeException("Order cannot be cancelled as it is already " + order.getShipmentStatus());
        }

        order.setCancel(true);
        order.setShipmentStatus("cancelled");
        orderRepository.save(order);

        Book book = order.getBook();
        book.setBookQuantity(book.getBookQuantity() + order.getQuantity());
        bookRepository.save(book);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Order>> getAllOrders(String token) {
        DataHolder dataHolder=tokenUtility.decode(token);
        if(dataHolder.getRole().equalsIgnoreCase("ADMIN")) {
            List<Order> orders = orderRepository.findAll();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        else{
            throw new RuntimeException("Access denied for users");
        }
    }

    @Override
    public ResponseEntity<List<Order>> getAllOrdersForUser(String token) {
        Long userId = tokenUtility.decode(token).getId();
        List<Order> orders = orderRepository.findByUserIdAndCancel(userId, false);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Order>> getAllCancelOrders(String token){
        DataHolder dataHolder=tokenUtility.decode(token);
        if(dataHolder.getRole().equalsIgnoreCase("ADMIN")) {
            List<Order> orders = orderRepository.findByCancel(true);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        else{
            throw new RuntimeException("Access denied for users");
        }
    }

    @Override
    public ResponseEntity<List<Order>> getAllCancelOrdersForUser(String token){
        DataHolder dataHolder=tokenUtility.decode(token);
        List<Order> orders = orderRepository.findByUserIdAndCancel(dataHolder.getId(),true);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Order>> getAllPlacedOrders(String token){
        DataHolder dataHolder=tokenUtility.decode(token);
        if(dataHolder.getRole().equalsIgnoreCase("ADMIN")) {
            List<Order> orders = orderRepository.findByCancel(false);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        else{
            throw new RuntimeException("Access denied for users");
        }
    }

    @Override
    public ResponseEntity<List<Order>> getAllPlacedOrdersForUser(String token){
        DataHolder dataHolder=tokenUtility.decode(token);
        List<Order> orders = orderRepository.findByUserIdAndCancel(dataHolder.getId(),false);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
