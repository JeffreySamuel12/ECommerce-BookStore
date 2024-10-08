package com.example.BookStoreApplication.repository;

import com.example.BookStoreApplication.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findByUserIdAndBookId(Long userId, Long bookId);
}