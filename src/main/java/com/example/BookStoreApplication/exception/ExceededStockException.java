package com.example.BookStoreApplication.exception;

public class ExceededStockException extends RuntimeException {
    public ExceededStockException(String message) {
        super(message);
    }
}