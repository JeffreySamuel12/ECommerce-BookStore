package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.WishlistDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WishlistServiceInterface {

    ResponseEntity<WishlistDTO> addToWishlist(String token, long bookId);

    ResponseEntity<String> deleteFromWishlist(String token, long wishlistId);

    ResponseEntity<List<WishlistDTO>> getAllWishlist(String token);

}