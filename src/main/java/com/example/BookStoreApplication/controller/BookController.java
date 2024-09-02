package com.example.BookStoreApplication.controller;

import com.example.BookStoreApplication.dto.BookDTO;
import com.example.BookStoreApplication.model.Book;
import com.example.BookStoreApplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public Book addBook(@RequestHeader("Authorization") String token, @RequestBody BookDTO bookDTO) {
        System.out.println("Received request to add book: " + bookDTO);
        return bookService.addBook(bookDTO);
    }

    @GetMapping("/all")
    public List<BookDTO> getAllBooks(@RequestHeader("Authorization") String token) {
        System.out.println("Received request to get all books");
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        System.out.println("Received request to get book by ID: " + id);
        return bookService.getBookById(id);
    }

    @PutMapping("/update/{id}")
    public BookDTO updateBook(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody BookDTO bookDTO) {
        System.out.println("Received request to update book with ID: " + id + " with data: " + bookDTO);
        return bookService.updateBook(id, bookDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        System.out.println("Received request to delete book with ID: " + id);
        bookService.deleteBook(id);
    }

    @PutMapping("/changeQuantity/{id}/{quantity}")
    public BookDTO changeBookQuantity(@RequestHeader("Authorization") String token, @PathVariable long id, @PathVariable int quantity) {
        System.out.println("Received request to change quantity of book with ID: " + id + " to: " + quantity);
        return bookService.changeBookQuantity(id, quantity);
    }

    @PutMapping("/changePrice/{id}/{price}")
    public BookDTO changeBookPrice(@RequestHeader("Authorization") String token, @PathVariable long id, @PathVariable double price) {
        System.out.println("Received request to change price of book with ID: " + id + " to: " + price);
        return bookService.changeBookPrice(id, price);
    }
}
