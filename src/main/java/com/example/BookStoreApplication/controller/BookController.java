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
    public Book addBook(@RequestBody BookDTO bookDTO) {
        System.out.println("Received request to add book: " + bookDTO);
        return bookService.addBook(bookDTO);
    }

    @GetMapping("/all")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/update/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id, bookDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/changeQuantity/{id}/{quantity}")
    public BookDTO changeBookQuantity(@RequestHeader("Authorization") String token, @PathVariable long id, @PathVariable int quantity) {
        return bookService.changeBookQuantity(id, quantity);
    }

    @PutMapping("/changePrice/{id}/{price}")
    public BookDTO changeBookPrice(@RequestHeader("Authorization") String token, @PathVariable long id, @PathVariable double price) {
        return bookService.changeBookPrice(id, price);
    }
}
