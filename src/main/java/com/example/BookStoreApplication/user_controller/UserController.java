package com.example.BookStoreApplication.user_controller;

import com.example.BookStoreApplication.user_dto.LoginDTO;
import com.example.BookStoreApplication.user_dto.UserDTO;
import com.example.BookStoreApplication.user_model.User;
import com.example.BookStoreApplication.user_service.UserService;
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
    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserDTO userDTO=userService.getUserById(id).orElseThrow(()->new RuntimeException("User not found"));
        return ResponseEntity.ok(userDTO);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,@RequestBody UserDTO userDTO){
        UserDTO updatedUser=userService.updateUser(id,userDTO);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO){
        String s=userService.login(loginDTO);
        return s;
    }

    @GetMapping("/GetByToken")
    public UserDTO getByToken(@RequestHeader String token){
        return userService.getByToken(token);
    }
}

