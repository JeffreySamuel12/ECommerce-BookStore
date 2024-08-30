package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.CartDTO;
import com.example.BookStoreApplication.dto.DataHolder;
import com.example.BookStoreApplication.exception.UserNotFoundException;
import com.example.BookStoreApplication.model.Book;
import com.example.BookStoreApplication.model.Cart;
import com.example.BookStoreApplication.model.User;
import com.example.BookStoreApplication.repository.BookRepository;
import com.example.BookStoreApplication.repository.CartRepository;
import com.example.BookStoreApplication.repository.UserRepository;
import com.example.BookStoreApplication.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartServiceInterface{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TokenUtility tokenUtility;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;


    private CartDTO convertEntityToDTO(Cart cart) {

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setUserId(cart.getUser().getId());
        cartDTO.setBookId(cart.getBook().getId());
        cartDTO.setBookName(cart.getBook().getBookName());
        cartDTO.setAuthor(cart.getBook().getBookAuthor());
        cartDTO.setPrice(cart.getBook().getBookPrice());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setTotalPrice(cart.getTotalPrice());

        return cartDTO;
    }


    public ResponseEntity<CartDTO> addToCart(String token, Long bookId) {
        DataHolder dataHolder = tokenUtility.decode(token);
        Long userId = dataHolder.getId();
        String role = dataHolder.getRole();

        System.out.println(userId);
        System.out.println(role);

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("User found in the list");
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        System.out.println("Book found in the list");

        List<Cart> allCart=cartRepository.findAll();

        for (Cart cartOfUserBook :allCart)
        {
            if (cartOfUserBook.getUser().getId().equals(userId) && cartOfUserBook.getBook().getId().equals(bookId ))
            {
                return updateQuantity(token,cartOfUserBook.getCartId(),(int)cartOfUserBook.getQuantity()+1);
            }
        }
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setBook(book);
        cart.setQuantity(cart.getQuantity()+1);
        double totalPrice = book.getBookPrice() * cart.getQuantity();
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
        return new ResponseEntity<>(convertEntityToDTO(cart), HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteCartById(long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);

        if (cartOptional.isPresent()) {
            System.out.println("Cart id is present..");
            cartRepository.deleteById(cartId);
            return new ResponseEntity<>("Cart entity deleted Successfully", HttpStatus.OK);
        } else {
            throw new UserNotFoundException("Cart id Not Found...");
        }


    }


    public ResponseEntity<String> removeByUserId(String token) {
        DataHolder dataHolder = tokenUtility.decode(token);
        long userId = dataHolder.getId();
        System.out.println(userId);

        List<Cart> carts = cartRepository.findAll();
        if (carts.isEmpty()) {
            System.out.println("Not matching cart items found for the user.");
            throw new UserNotFoundException("Cart items for the user not found..");
        }
        for (Cart items : carts) {
            if (items.getUser().getId() == userId) {
                cartRepository.delete(items);
            }
        }


        return new ResponseEntity<>("Deleted the cart for the user", HttpStatus.OK);
    }


    public ResponseEntity<CartDTO> updateQuantity(String token, long cartId, int quantity) {
        DataHolder dataHolder = tokenUtility.decode(token);
        Long userId = dataHolder.getId();

        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setQuantity(quantity);
            cart.setTotalPrice((long) (cart.getBook().getBookPrice() * (long) quantity));
            cartRepository.save(cart);
            CartDTO cartDTO = convertEntityToDTO(cart);
            return new ResponseEntity<>(cartDTO, HttpStatus.OK);

        } else {
            throw new UserNotFoundException("cart empty for the user");
        }


    }


    public ResponseEntity<List<CartDTO>> getAllCartItemsForUser(String token) {
        DataHolder dataHolder = tokenUtility.decode(token);
        long userId = dataHolder.getId();


        List<Cart> carts = cartRepository.findAll();
        List<CartDTO> allUser = new ArrayList<>();
        for (Cart items : carts) {

            if (items.getUser().getId() == userId) {
                allUser.add(convertEntityToDTO(items));
            }
        }


        return new ResponseEntity<>(allUser, HttpStatus.OK);


    }


    public ResponseEntity<List<CartDTO>> getAllCartItems(String token) {
        DataHolder dataHolder = tokenUtility.decode(token);
        long userId = dataHolder.getId();
        String role = dataHolder.getRole();
        if (role.equalsIgnoreCase("admin")) {
            List<Cart> carts = cartRepository.findAll();
            List<CartDTO> allUser = new ArrayList<>();

            for (Cart items : carts) {
                allUser.add(convertEntityToDTO(items));
            }

            return new ResponseEntity<>(allUser, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("You does not have proper permissions to view it .");
        }

    }

}
