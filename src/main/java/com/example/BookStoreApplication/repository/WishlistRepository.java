package com.example.BookStoreApplication.repository;

import com.example.BookStoreApplication.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {

}
