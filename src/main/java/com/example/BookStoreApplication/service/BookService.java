package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.BookDTO;
import com.example.BookStoreApplication.model.Book;
import com.example.BookStoreApplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;


    public Book addBook(BookDTO bookDTO) {
        System.out.println("Adding book: " + bookDTO);
        Book newBook = new Book(bookDTO);
        Book savedBook = bookRepository.save(newBook);
        System.out.println("Saved book: " + savedBook);
        return savedBook;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setBookName(bookDetails.getBookName());
        book.setBookAuthor(bookDetails.getBookAuthor());
        book.setBookDescription(bookDetails.getBookDescription());
        book.setBookLogo(bookDetails.getBookLogo());
        book.setBookPrice(bookDetails.getBookPrice());
        book.setBookQuantity(bookDetails.getBookQuantity());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book changeBookQuantity(Long id, Integer quantity) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setBookQuantity(quantity);
        return bookRepository.save(book);
    }

    public Book changeBookPrice(Long id, Double price) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setBookPrice(price);
        return bookRepository.save(book);
    }

    public void saveFile(MultipartFile file) {
        try {
            Path path = Paths.get("uploads/" + file.getOriginalFilename());
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
}
