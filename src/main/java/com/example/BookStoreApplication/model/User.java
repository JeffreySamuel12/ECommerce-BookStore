package com.example.BookStoreApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_table")
public class User {


    @Id
    @GeneratedValue
    private Long userId;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "registered_date")
    private Date registeredDate;
    @Column(name = "updated_date")
    private Date updatedDate;
    @Column(name = "password")
    private String password;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "role")
    private String role;


}
