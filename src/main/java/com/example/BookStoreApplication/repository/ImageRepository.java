package com.example.BookStoreApplication.repository;


import com.example.BookStoreApplication.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}