package com.example.BookStoreApplication.exception;

public class CartIdNotFoundException extends RuntimeException {
    public CartIdNotFoundException(String message) {
        super(message);
    }
}