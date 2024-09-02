package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.CartDTO;
import com.example.BookStoreApplication.dto.WishlistDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WishlistServiceInterface {

    public ResponseEntity<WishlistDTO> addToWishlist(String token, long bookId);
    public ResponseEntity<String> deleteFromWishlist(String token, long wishlistId);
    public ResponseEntity<List<WishlistDTO>> getAllWishlist(String token);

}
