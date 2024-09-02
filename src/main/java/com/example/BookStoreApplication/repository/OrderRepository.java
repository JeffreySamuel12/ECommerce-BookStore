package com.example.BookStoreApplication.repository;

import com.example.BookStoreApplication.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByCancel(boolean cancel);
    List<Order> findByUserIdAndCancel(Long userId, boolean cancel);

    List<Order> findByCancelForUser(Long id, boolean b);

}