package com.example.BookStoreApplication.repository;

import com.example.BookStoreApplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

