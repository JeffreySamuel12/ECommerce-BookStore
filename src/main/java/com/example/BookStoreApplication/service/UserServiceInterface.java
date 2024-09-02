package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.LoginDTO;
import com.example.BookStoreApplication.dto.UserDTO;
import com.example.BookStoreApplication.model.User;

import java.util.List;

public interface UserServiceInterface {

    public UserDTO registerUser(User user);

    public String login(LoginDTO loginDTO);

    public List<UserDTO> getAllUsers(String token);

    public UserDTO getUserById(String token);

    public UserDTO updateUser(String token, UserDTO userDTO);

    public void deleteUser(String token);

    public UserDTO convertToDTO(User user);

    public UserDTO getByToken(String token);

}