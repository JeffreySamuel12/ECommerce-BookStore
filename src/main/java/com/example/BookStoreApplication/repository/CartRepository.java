package com.example.BookStoreApplication.repository;

import com.example.BookStoreApplication.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CartRepository extends JpaRepository<Cart, Long> {

}
