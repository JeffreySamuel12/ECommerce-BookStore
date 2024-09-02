package com.example.BookStoreApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wishlistId;

    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

}
