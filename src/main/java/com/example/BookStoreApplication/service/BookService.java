package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.BookDTO;
import com.example.BookStoreApplication.model.Book;
import com.example.BookStoreApplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        return bookRepository.save(book);
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return convertToDTO(book);
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book bookDetails = convertToEntity(bookDTO);
        bookDetails.setId(id); // Ensure the ID is set for update
        Book updatedBook = bookRepository.save(bookDetails);
        return convertToDTO(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public BookDTO changeBookQuantity(Long id, int quantity) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setBookQuantity((long) quantity); // Convert int to Long
        Book updatedBook = bookRepository.save(book);
        return convertToDTO(updatedBook);
    }

    public BookDTO changeBookPrice(Long id, double price) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setBookPrice(price);
        Book updatedBook = bookRepository.save(book);
        return convertToDTO(updatedBook);
    }

    private BookDTO convertToDTO(Book book) {
        return BookDTO.builder()
                .bookId(book.getId()) // Updated method name
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
                .id(bookDTO.getBookId()) // Updated method name
                .bookName(bookDTO.getBookName())
                .bookAuthor(bookDTO.getBookAuthor())
                .bookDescription(bookDTO.getBookDescription())
                .bookLogo(bookDTO.getBookLogo())
                .bookPrice(bookDTO.getBookPrice())
                .bookQuantity(bookDTO.getBookQuantity())
                .build();
    }
}
