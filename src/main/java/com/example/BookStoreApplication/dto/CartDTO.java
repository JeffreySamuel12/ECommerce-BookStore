package com.example.BookStoreApplication.dto;

//public class CartDTO {
//}


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {


    private long cartId;
    private long userId;
    private long bookId;
    private String bookName;
    private String author;
    private double price;
    private long quantity;
    private double totalPrice;

}
