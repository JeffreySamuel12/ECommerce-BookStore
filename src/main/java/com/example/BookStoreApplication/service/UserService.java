package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.DataHolder;
import com.example.BookStoreApplication.dto.LoginDTO;
import com.example.BookStoreApplication.dto.UserDTO;
import com.example.BookStoreApplication.model.User;
import com.example.BookStoreApplication.repository.UserRepository;
import com.example.BookStoreApplication.util.TokenUtility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtility tokenUtility;

    public UserDTO registerUser(User user) {
        user.setUpdatedDate(new Date());
        user.setUpdatedDate(new Date());
        userRepository.save(user);
        return convertToDTO(user);
    }

    public String login(LoginDTO loginDTO) {
        String userEmail = loginDTO.getEmailId();
        String userPassword = loginDTO.getPassword();
        System.out.println(userEmail);
        System.out.println(userPassword);
        User check = userRepository.findByemailId(userEmail);
        System.out.println(check.getUserId());
        if (userEmail.equals(check.getEmailId()) && (userPassword.equals(check.getPassword()))) {
            String token = tokenUtility.getToken(check.getUserId(), check.getRole());
            return token;
        }
        throw new RuntimeException("User or password invalid!");
    }

    public List<UserDTO> getAllUsers(String token) {
        DataHolder dataHolder = tokenUtility.decode(token);
        if (dataHolder.getRole().equals("ADMIN")) {
            return userRepository.findAll().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Access denied for the user");
        }
    }

    public UserDTO getUserById(String token) {
        return getByToken(token);
    }

    public UserDTO updateUser(String token, UserDTO userDTO) {
        DataHolder dataHolder = tokenUtility.decode(token);
        if (dataHolder.getId().equals(userDTO.getId())) {
            User user = userRepository.findById(dataHolder.getId()).orElseThrow(() -> new RuntimeException("User not found"));
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setDob(userDTO.getDob());
            user.setRegisteredDate(userDTO.getRegisteredDate());
            user.setUpdatedDate(new Date());
            user.setEmailId(userDTO.getEmailId());
            user.setRole(userDTO.getRole());
            userRepository.save(user);
            return convertToDTO(user);
        } else {
            throw new RuntimeException("UserId mismatch");
        }
    }

    public void deleteUser(String token) {
        DataHolder dataHolder = tokenUtility.decode(token);
        User user = userRepository.findById(dataHolder.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    public UserDTO convertToDTO(@Valid User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setDob(user.getDob());
        userDTO.setRegisteredDate(user.getRegisteredDate());
        userDTO.setUpdateDate(user.getUpdatedDate());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public UserDTO getByToken(String token) {
        DataHolder dataHolder = tokenUtility.decode(token);
        return convertToDTO(userRepository.findById(dataHolder.getId()).orElseThrow(() -> new RuntimeException("Id not found")));
    }
}
