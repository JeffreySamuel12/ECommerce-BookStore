package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.BookDTO;
import com.example.BookStoreApplication.dto.DataHolder;
import com.example.BookStoreApplication.model.Book;
import com.example.BookStoreApplication.repository.BookRepository;
import com.example.BookStoreApplication.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TokenUtility tokenUtility;

    public Book addBook(String token,BookDTO bookDTO) {
        DataHolder dataHolder=tokenUtility.decode(token);
        if(dataHolder.getRole().equalsIgnoreCase("ADMIN")) {
            Book book = convertToEntity(bookDTO);
            return bookRepository.save(book);
        }
        else{
            throw new RuntimeException("Access denied");
        }
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
        Book updatedBook = bookRepository.save(bookDetails);
        return convertToDTO(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public BookDTO changeBookQuantity(String token,Long id,Long quantity) {
        DataHolder dataHolder=tokenUtility.decode(token);
        if(dataHolder.getRole().equalsIgnoreCase("ADMIN")) {
            Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
            book.setBookQuantity(quantity);
            Book updatedBook = bookRepository.save(book);
            return convertToDTO(updatedBook);
        }
        else{
            throw new RuntimeException("Access denied");
        }
    }

    public BookDTO changeBookPrice(String token,Long id, double price) {
        DataHolder dataHolder=tokenUtility.decode(token);
        if(dataHolder.getRole().equalsIgnoreCase("ADMIN")) {
            Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
            book.setBookPrice(price);
            Book updatedBook = bookRepository.save(book);
            return convertToDTO(updatedBook);
        }
        else{
            throw new RuntimeException("Access denied");
        }
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO=new BookDTO();
        bookDTO.setBookId(book.getId());
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
