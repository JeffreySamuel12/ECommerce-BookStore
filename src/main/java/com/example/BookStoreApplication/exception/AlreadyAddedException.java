package com.example.BookStoreApplication.exception;

public class AldreadyAddedException extends RuntimeException {
    public AldreadyAddedException(String message) {
        super(message);
    }

}