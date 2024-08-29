package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.LoginDTO;
import com.example.BookStoreApplication.dto.UserDTO;
import com.example.BookStoreApplication.model.User;

import java.util.List;

public interface UserService_interface {
    public  abstract UserDTO registerUser(User user);

    public abstract String login(LoginDTO loginDTO);

    public abstract List<UserDTO> getAllUsers(String token);

    public abstract UserDTO getUserById(String token);

    public abstract UserDTO updateUser(String token, UserDTO userDTO);

    public abstract void deleteUser(String token);

    public abstract UserDTO convertToDTO(User user);

    public abstract UserDTO getByToken(String token);


}
