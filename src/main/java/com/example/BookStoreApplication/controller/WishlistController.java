package com.example.BookStoreApplication.controller;

import com.example.BookStoreApplication.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    /**
     * Purpose: This API is created for adding a product based on the bookId to the wishlist
     * @param token used to find the user accessing the API
     * @param bookId used to find which product is being added to the wishlist
     * @return returns ResponseEntity which holds the WishlistDTO object along with the response code
     */
    @PostMapping("/add/{bookId}")
    public ResponseEntity<?> addToWishlist(@RequestHeader String token, @PathVariable long bookId)
    {
        return wishlistService.addToWishlist(token,bookId);
    }

    /**
     * Deletes a product from the wishlist based on the wishlistId.
     *
     * @param token the token used to identify the user accessing the API
     * @param wishlistId the ID of the wishlist item to be deleted
     * @return a ResponseEntity containing a message and the response code
     */
    @DeleteMapping("/delete/{wishlistId}")
    public ResponseEntity<?> deleteFromWishlist(@RequestHeader String token,@PathVariable long wishlistId)
    {
        return wishlistService.deleteFromWishlist(token,wishlistId);
    }

    /**
     * Retrieves all wishlist items.
     *
     * @param token the token used to identify the user accessing the API and to check and restrict the access to admin
     * @return a ResponseEntity containing a list of WishlistDTO objects and the response code
     */
    @GetMapping("/showAll")
    public ResponseEntity<?> getAllWishlist(@RequestHeader String token)
    {
        return wishlistService.getAllWishlist(token);
    }

    /**
     * Retrieves the wishlist items for the authenticated user.
     *
     * @param token the token used to identify the user accessing the API
     * @return a ResponseEntity containing a list of WishlistDTO objects and the response code
     */
    @GetMapping("/myWishList")
    public ResponseEntity<?> getUserWishlist(@RequestHeader String token)
    {
        return wishlistService.getUserWishlist(token);
    }
}
