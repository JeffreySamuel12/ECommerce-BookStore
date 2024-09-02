package com.example.BookStoreApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedback_table")
public class Feedback {
    @Id
    @GeneratedValue
    private Long feedback_id;

    private Long product_id;

    private String comment;

    @Min(value = 0,message = "Rating should not be less than 0")
    @Max(value = 5,message = "Rating should be within the range of 5")
    private Double rating;

    public Long getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(Long feedback_id) {
        this.feedback_id = feedback_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public @Min(value = 0, message = "Rating should not be less than 0") @Max(value = 5, message = "Rating should be within the range ofS 5") Double getRating() {
        return rating;
    }

    public void setRating(@Min(value = 0, message = "Rating should not be less than 0") @Max(value = 5, message = "Rating should be within the range ofS 5") Double rating) {
        this.rating = rating;
    }
}