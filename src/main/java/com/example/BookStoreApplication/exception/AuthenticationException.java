package com.example.BookStoreApplication.exception;


public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message){
        super(message);
    }
}
