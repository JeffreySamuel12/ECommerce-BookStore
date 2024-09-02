package com.example.BookStoreApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.*;

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

    @ExceptionHandler(AlreadyAddedException.class)
    public ResponseEntity<?> AlreadyAddedException(AlreadyAddedException alreadyAddedException){
        return  new ResponseEntity<>(alreadyAddedException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExceededStockException.class)
    public ResponseEntity<?> ExceededStockException(ExceededStockException exceededStockException){
        return  new ResponseEntity<>(exceededStockException.getMessage(),HttpStatus.BAD_REQUEST);
    }






    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> UserNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String,String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}