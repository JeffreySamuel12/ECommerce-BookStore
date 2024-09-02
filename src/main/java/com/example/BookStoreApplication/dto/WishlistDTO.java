package com.example.BookStoreApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDTO {

    private long wishlistId;
    private long userId;
    private long bookId;
    private String bookName;
    private double price;
    private boolean availability;
}
