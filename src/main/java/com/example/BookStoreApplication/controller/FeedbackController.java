package com.example.BookStoreApplication.controller;

import com.example.BookStoreApplication.model.Feedback;
import com.example.BookStoreApplication.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * Purpose: Adds feedback for a specific product.
     *
     * @param product_id The ID of the product for which feedback is being added.
     * @param feedback The feedback object containing user feedback details.
     * @return ResponseEntity<String> indicating the success or failure of the operation.
     *
     * Input:
     * - product_id: Long - The ID of the product.
     * - feedback: Feedback - The feedback object containing details like rating, comments, etc.
     *
     * Output:
     * - ResponseEntity<String> - A response entity with a message indicating the result of the operation.
     */
    @PostMapping("/addFeedback/{product_id}")
    public ResponseEntity<String> addFeedback(@RequestHeader String token,@PathVariable Long product_id,@Valid @RequestBody Feedback feedback){
        return feedbackService.addFeedback(token,product_id,feedback);
    }

    /**
     * Purpose: Retrieves all feedbacks.
     *
     * @param token The authentication token for verifying the request.
     * @return ResponseEntity<?> containing a list of all feedbacks or an error message.
     *
     * Input:
     * - token: String - The authentication token.
     *
     * Output:
     * - ResponseEntity<?> - A response entity containing a list of all feedbacks or an error message.
     */
    @GetMapping
    public ResponseEntity<?> getAllFeedbacks(@RequestHeader String token){
        return feedbackService.getAllFeedbacks(token);
    }

    /**
     * Purpose: Retrieves feedback for a specific product by its ID.
     *
     * @param product_id The ID of the product for which feedback is being retrieved.
     * @return ResponseEntity<?> containing the feedback for the specified product or an error message.
     *
     * Input:
     * - product_id: Long - The ID of the product.
     *
     * Output:
     * - ResponseEntity<?> - A response entity containing the feedback for the specified product or an error message.
     */
    @GetMapping("/getFeedbackByProductId/{product_id}")
    public ResponseEntity<?> getFeedbackByProductId(@PathVariable Long product_id){
        return feedbackService.getFeedbackByProductId(product_id);
    }

    /**
     * Purpose: Updates feedback by its ID.
     *
     * @param feedback_id The ID of the feedback to be updated.
     * @param feedback The feedback object containing updated details.
     * @return ResponseEntity<?> indicating the success or failure of the operation.
     *
     * Input:
     * - feedback_id: Long - The ID of the feedback to be updated.
     * - feedback: Feedback - The feedback object containing updated details like rating, comments, etc.
     *
     * Output:
     * - ResponseEntity<?> - A response entity with a message indicating the result of the operation.
     */
    @PutMapping("/updateFeedbackById/{feedback_id}")
    public ResponseEntity<?> updateFeedbackById(@PathVariable Long feedback_id,@RequestHeader Feedback feedback){
        return feedbackService.updateFeedbackById(feedback_id,feedback);
    }
}
