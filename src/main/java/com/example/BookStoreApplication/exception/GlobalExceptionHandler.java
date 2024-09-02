package com.example.BookStoreApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleGlobalException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> iOException(IOException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> UserNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> BookNotFoundException(BookNotFoundException bookNotFoundException){
        return  new ResponseEntity<>(bookNotFoundException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartIdNotFoundException.class)
    public ResponseEntity<?> CartIdNotFoundException(CartIdNotFoundException cartIdNotFoundException){
        return  new ResponseEntity<>(cartIdNotFoundException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoPermissionFoundException.class)
    public ResponseEntity<?> NoPermissionFoundException(NoPermissionFoundException noPermissionFoundException){
        return  new ResponseEntity<>(noPermissionFoundException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AldreadyAddedException.class)
    public ResponseEntity<?> AldreadyAddedException(AldreadyAddedException aldreadyAddedException){
        return  new ResponseEntity<>(aldreadyAddedException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExceededStockException.class)
    public ResponseEntity<?> ExceededStockException(ExceededStockException exceededStockException){
        return  new ResponseEntity<>(exceededStockException.getMessage(),HttpStatus.BAD_REQUEST);
    }






}