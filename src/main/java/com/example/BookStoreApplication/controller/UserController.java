package com.example.BookStoreApplication.controller;

import com.example.BookStoreApplication.dto.LoginDTO;
import com.example.BookStoreApplication.dto.UserDTO;
import com.example.BookStoreApplication.model.User;
import com.example.BookStoreApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody User user){
        return  userService.registerUser(user);
    }

    @GetMapping("/getUsers")
    public List<UserDTO> getAllUsers(@RequestHeader String token){
        return userService.getAllUsers(token);
    }

    @GetMapping("/read")
    public UserDTO getUserByToken(@RequestHeader String token) {
        return userService.getUserById(token);
    }

    @PutMapping("/update")
    public UserDTO updateUserByToken(@RequestHeader String token,@RequestBody UserDTO userDTO){
        return userService.updateUser(token,userDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestHeader String token) {
        userService.deleteUser(token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO){
        String s=userService.login(loginDTO);
        return s;
    }

    @GetMapping("/GetUserByToken")
    public UserDTO getByToken(@RequestHeader String token){
        return userService.getByToken(token);
    }
}

