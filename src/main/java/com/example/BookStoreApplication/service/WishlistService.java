package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.DataHolder;
import com.example.BookStoreApplication.dto.WishlistDTO;
import com.example.BookStoreApplication.exception.AlreadyAddedException;
import com.example.BookStoreApplication.exception.NoPermissionFoundException;
import com.example.BookStoreApplication.exception.UserNotFoundException;
import com.example.BookStoreApplication.model.Book;
import com.example.BookStoreApplication.model.User;
import com.example.BookStoreApplication.model.Wishlist;
import com.example.BookStoreApplication.repository.BookRepository;
import com.example.BookStoreApplication.repository.UserRepository;
import com.example.BookStoreApplication.repository.WishlistRepository;
import com.example.BookStoreApplication.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class WishlistService implements WishlistServiceInterface{

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TokenUtility tokenUtility;

    private boolean checkAvailability(Book book) {
        if(book.getBookQuantity() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public WishlistDTO convertWishlistToDTO(Wishlist wishlist)
    {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setWishlistId(wishlist.getWishlistId());
        wishlistDTO.setUserId(wishlist.getUser().getUserId());
        wishlistDTO.setBookId(wishlist.getBook().getId());
        wishlistDTO.setBookName(wishlist.getBook().getBookName());
        wishlistDTO.setPrice(wishlist.getBook().getBookPrice());
        wishlistDTO.setAvailability(checkAvailability(wishlist.getBook()));

        return wishlistDTO;
    }

    public ResponseEntity<WishlistDTO> addToWishlist(String token, long bookId)
    {
        DataHolder dataHolder = tokenUtility.decode(token);
        Long userId = dataHolder.getId();

        User user= userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User is not found "));
        System.out.println("User found in the list");

        Book book= bookRepository.findById(bookId).orElseThrow(()->new UserNotFoundException("Book not found"));
        System.out.println("Book found in the list");

        List<Wishlist> wishlists=wishlistRepository.findAll();

        for(Wishlist item:wishlists)
        {
            if (item.getUser().getUserId().equals(userId) && item.getBook().getId().equals(bookId) )
            {
                throw new AlreadyAddedException("Already added to wishlist");
            }
        }

        Wishlist wishlist= new Wishlist();
        wishlist.setUser(user);
        wishlist.setBook(book);

        wishlistRepository.save(wishlist);
        return  new ResponseEntity<>(convertWishlistToDTO(wishlist), HttpStatus.CREATED);
    }

    public ResponseEntity<List<WishlistDTO>> getAllWishlist(String token)
    {
        DataHolder dataHolder = tokenUtility.decode(token);
        String role = dataHolder.getRole();

        if (role.equalsIgnoreCase("admin"))
        {
            List<Wishlist> wishlists = wishlistRepository.findAll();
            List<WishlistDTO> allUsers= new ArrayList<>();

            for(Wishlist item: wishlists)
            {
                allUsers.add(convertWishlistToDTO(item));
            }

            return new ResponseEntity<>(allUsers,HttpStatus.OK);
        }
        else {
            throw new NoPermissionFoundException("You does not have proper permission to view it ");
        }
    }

    public ResponseEntity<String> deleteFromWishlist(String token, long wishlistId)
    {
        DataHolder dataHolder = tokenUtility.decode(token);
        Long userId = dataHolder.getId();
        if (userRepository.findById(userId).isPresent()) {
            System.out.println("User found");
            List<Wishlist> allWishlist= wishlistRepository.findAll();

            for(Wishlist item: allWishlist)
            {
                if (item.getWishlistId()==wishlistId)
                {
                    wishlistRepository.delete(item);
                    System.out.println("Deleted"+ item);
                    return new ResponseEntity<>("Deleted the book from the wishlist of the user",HttpStatus.OK);
                }
            }
        }
        else {
            throw new UserNotFoundException("User not found");
        }
        return new ResponseEntity<>("Could not find the item to delete ",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<WishlistDTO>> getUserWishlist(String token)
    {
        DataHolder dataHolder = tokenUtility.decode(token);
        long userId = dataHolder.getId();

        User user =userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));

        List<Wishlist> wishlists= wishlistRepository.findAll();

        List<WishlistDTO> userWishlist= new ArrayList<>();

        for (Wishlist item: wishlists)
        {
            if (item.getUser().getUserId()==userId)
            {
                userWishlist.add(convertWishlistToDTO(item));
            }
        }

        return  new ResponseEntity<>(userWishlist,HttpStatus.OK);
    }
}