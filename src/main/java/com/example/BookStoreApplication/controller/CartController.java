package com.example.BookStoreApplication.controller;

import com.example.BookStoreApplication.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    /**
     * Purpose: this API is created for adding to the cart
     * @param token used to find the the user
     * @param bookId to find which book details has to be added for creation of cart
     * @return returns the responseEntity which holds the UserDTO object with the response code
     */
    @PostMapping("/addtocart/{bookId}")
    public ResponseEntity<?> addToCart(@RequestHeader String token, @PathVariable Long bookId) {
        System.out.println("atleast");
        return cartService.addToCart(token, bookId);
    }

    /**
     * The following API has the functionality to delete the cart by the cart id
     * @param cartId helps to find which tuple has to be deleted
     * @return returns the Respnse Entity holding a string along with the response code.
     */
    @DeleteMapping("/deletecartbyid/{cartId}")
    public ResponseEntity<?> deleteCartById(@PathVariable long cartId) {
        return cartService.deleteCartById(cartId);
    }


    /**
     *This API allows a user to remove all his cart
     *
     * @param token is taken to identify the user who is accessing the api can delete his cart
     * @return contains responseEntity  having String message with the response status.
     */
    @DeleteMapping("/removebyuserid")
    public ResponseEntity<?> removeByUserId(@RequestHeader String token) {
        return cartService.removeByUserId(token);
    }


    /**
     * This API helps to update the quantity of a specific cartId for the user
     *
     * @param token used to find which user is accessing the API
     * @param cartId used to get which cart tuple to be updated for that user
     * @param quantity to update the quantity of the book from previous amount to quantity value mentioned
     * @return returns the ResponseEntity with the CartDTO object with the response status
     */
    @PutMapping("/updatequantity/{cartId}/{quantity}")
    public ResponseEntity<?> updateQuantity(@RequestHeader String token, @PathVariable long cartId, @PathVariable int quantity) {
        return cartService.updateQuantity(token, cartId, quantity);
    }


    /**
     *This api is used to get  all the cart information of the user who is accessing the API
     * @param token used to get the information of who the user is accessing the API
     * @return returns the ResponseEntity with the list of all CartDTO object along with the response status
     */
    @GetMapping("/getallcartofuser")
    public ResponseEntity<?> getAllCartItemsForUser(@RequestHeader String token) {
        return cartService.getAllCartItemsForUser(token);
    }

    /**
     * This API is used to get all the present cart items in the cart table where this API is restricted to Admin
     * @param token it is used to identify the users role , which helps us to allow access of getting all the cart items only to the admin user.
     * @return returns the ResponseEntity with List of CartDTO objects along with the response status
     */
    @GetMapping("/getallcartitems")
    public ResponseEntity<?> getAllCartItems(@RequestHeader String token) {
        return cartService.getAllCartItems(token);
    }


}