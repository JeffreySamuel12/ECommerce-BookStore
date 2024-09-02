package com.example.BookStoreApplication.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    private String bookName;
    private String author;
    private String bookDescription;
    private String logo;
    private double price;
    private int quantity;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Cart> carts;
}
