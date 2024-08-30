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
    public BookDTO updateBook(@PathVariable Long id, @RequestPart("book") BookDTO bookDTO, @RequestPart("file") MultipartFile file) {
        Book bookDetails = convertToEntity(bookDTO);
        bookDetails.setBookLogo(file.getOriginalFilename()); // Save the file name
        bookService.saveFile(file); // Save the file to a directory
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return convertToDTO(updatedBook);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/changeQuantity/{id}")
    public BookDTO changeBookQuantity(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestParam Long quantity) {
        Book updatedBook = bookService.changeBookQuantity(id, quantity);
        return convertToDTO(updatedBook);
    }

    @PutMapping("/changePrice/{id}")
    public BookDTO changeBookPrice(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestParam Double price) {
        Book updatedBook = bookService.changeBookPrice(id, price);
        return convertToDTO(updatedBook);
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO=new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setBookName(book.getBookName());
        bookDTO.setBookAuthor(book.getBookAuthor());
        bookDTO.setBookDescription(book.getBookDescription());
        bookDTO.setBookLogo(book.getBookLogo());
        bookDTO.setBookPrice(book.getBookPrice());
        bookDTO.setBookQuantity(book.getBookQuantity());
        return bookDTO;
    }

    private Book convertToEntity(BookDTO bookDTO) {
        Book book=new Book(bookDTO);
        return book;
    }
}
