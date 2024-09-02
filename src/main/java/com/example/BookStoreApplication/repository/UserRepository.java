package com.example.BookStoreApplication.repository;

import com.example.BookStoreApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "Select * from users_table where email_id=:userEmail", nativeQuery = true)
    User findByemailId(String userEmail);
}



