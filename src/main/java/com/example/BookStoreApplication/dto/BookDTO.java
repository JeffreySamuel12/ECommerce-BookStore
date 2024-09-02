package com.example.BookStoreApplication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private Long bookId;

    @NotBlank(message = "Book Name is required")
    private String bookName;

    private String bookAuthor;
    private String bookDescription;
    private String bookLogo;
    private Double bookPrice;
    private Integer bookQuantity;

}