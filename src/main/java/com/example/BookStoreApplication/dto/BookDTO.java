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
    private Long bookQuantity;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public @NotBlank(message = "Book Name is required") String getBookName() {
        return bookName;
    }

    public void setBookName(@NotBlank(message = "Book Name is required") String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookLogo() {
        return bookLogo;
    }

    public void setBookLogo(String bookLogo) {
        this.bookLogo = bookLogo;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Long getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(Long bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
}