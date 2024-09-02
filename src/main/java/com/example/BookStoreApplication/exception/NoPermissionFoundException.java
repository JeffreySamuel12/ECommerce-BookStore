package com.example.BookStoreApplication.exception;

public class NoPermissionFoundException extends RuntimeException {
    public NoPermissionFoundException(String message) {
        super(message);
    }
}