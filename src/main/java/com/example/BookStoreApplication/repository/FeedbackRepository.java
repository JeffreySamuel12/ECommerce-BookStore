package com.example.BookStoreApplication.repository;

import com.example.BookStoreApplication.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

    @Query(value = "Select * from feedback_table where product_id=:productId",nativeQuery = true)
    public List<Feedback> findByProductId(Long productId);
}
