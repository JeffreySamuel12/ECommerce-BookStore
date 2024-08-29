package com.example.BookStoreApplication.controller;

import com.example.BookStoreApplication.dto.BookDTO;
import com.example.BookStoreApplication.model.Book;
import com.example.BookStoreApplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Book> books = bookService.getAllBooks();
        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return convertToDTO(book);
    }

    @PutMapping("/update/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Book bookDetails = convertToEntity(bookDTO);// Save the file name
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return convertToDTO(updatedBook);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/changeQuantity/{id}/{quantity}")
    public BookDTO changeBookQuantity(@RequestHeader("Authorization") String token, @PathVariable long id, @PathVariable int quantity) {
        Book updatedBook = bookService.changeBookQuantity(id, quantity);

        return convertToDTO(updatedBook);
    }

    @PutMapping("/changePrice/{id}/{price}")
    public BookDTO changeBookPrice(@RequestHeader("Authorization") String token, @PathVariable long id, @PathVariable double price) {
        Book updatedBook = bookService.changeBookPrice(id, price);
        return convertToDTO(updatedBook);
    }

    private BookDTO convertToDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .bookAuthor(book.getBookAuthor())
                .bookDescription(book.getBookDescription())
                .bookLogo(book.getBookLogo())
                .bookPrice(book.getBookPrice())
                .bookQuantity(book.getBookQuantity())
                .build();
    }

    private Book convertToEntity(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .bookName(bookDTO.getBookName())
                .bookAuthor(bookDTO.getBookAuthor())
                .bookDescription(bookDTO.getBookDescription())
                .bookLogo(bookDTO.getBookLogo())
                .bookPrice(bookDTO.getBookPrice())
                .bookQuantity(bookDTO.getBookQuantity())
                .build();
    }
}
