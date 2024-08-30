package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.CartDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartServiceInterface {

    public ResponseEntity<CartDTO> addToCart(String token, Long bookId);

    public ResponseEntity<String> deleteCartById(long cartId);

    public ResponseEntity<String> removeByUserId(String token);

    public ResponseEntity<CartDTO> updateQuantity(String token, long cartId, int quantity);

    public ResponseEntity<List<CartDTO>> getAllCartItemsForUser(String token);

    public ResponseEntity<List<CartDTO>> getAllCartItems(String token);

}