package com.example.BookStoreApplication.model;

import com.example.BookStoreApplication.dto.BookDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private String bookLogo;
    private Double bookPrice;
    private Long bookQuantity;

    public Book(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.bookAuthor = bookDTO.getBookAuthor();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookLogo = bookDTO.getBookLogo();
        this.bookPrice = bookDTO.getBookPrice();
        this.bookQuantity = bookDTO.getBookQuantity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
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
