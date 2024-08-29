package com.example.BookStoreApplication.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Date registeredDate;
    private Date updateDate;

    @Email(message = "Email should be in proper format")
    private String emailId;
    private String role;


}

