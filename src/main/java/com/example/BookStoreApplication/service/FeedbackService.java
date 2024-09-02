package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.DataHolder;
import com.example.BookStoreApplication.model.Book;
import com.example.BookStoreApplication.model.Feedback;
import com.example.BookStoreApplication.repository.BookRepository;
import com.example.BookStoreApplication.repository.FeedbackRepository;
import com.example.BookStoreApplication.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TokenUtility tokenUtility;

    public ResponseEntity<String> addFeedback(String token,Long productId, Feedback feedback) {
        DataHolder dataHolder=tokenUtility.decode(token);
        if(dataHolder.getRole().equalsIgnoreCase("User")) {
            Book book = bookRepository.findById(productId).orElse(null);
            if (book != null) {
                feedback.setProduct_id(productId);
                feedbackRepository.save(feedback);
                return new ResponseEntity<>("Feedback submitted", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity<>("Admin can't add the feedback",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<?> getAllFeedbacks(String token) {
        DataHolder dataHolder=tokenUtility.decode(token);
        if(dataHolder.getRole().equalsIgnoreCase("ADMIN")){
            return new ResponseEntity<>(feedbackRepository.findAll(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Access denied for users",HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<?> getFeedbackByProductId(Long productId) {
        Book book=bookRepository.findById(productId).orElse(null);
        if(book!=null){
            List<Feedback> getFeedbacks=feedbackRepository.findByProductId(productId);
            if(getFeedbacks.isEmpty()){
                return new ResponseEntity<>("No feedback found for this Product",HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(getFeedbacks, HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateFeedbackById(Long feedbackId,Feedback update_feedback) {
        Feedback feedback=feedbackRepository.findById(feedbackId).orElseThrow(()->new RuntimeException("Feedback not found for this Id"));
        feedback.setProduct_id(update_feedback.getProduct_id());
        feedback.setComment(update_feedback.getComment());
        feedback.setRating(update_feedback.getRating());
        feedbackRepository.save(feedback);
        return new ResponseEntity<>("Feedback updated successfully",HttpStatus.OK);
    }
}