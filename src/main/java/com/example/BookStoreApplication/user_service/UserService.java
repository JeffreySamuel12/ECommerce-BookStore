package com.example.BookStoreApplication.user_service;

import com.example.BookStoreApplication.exception.AuthenticationException;
import com.example.BookStoreApplication.user_dto.DataHolder;
import com.example.BookStoreApplication.user_dto.LoginDTO;
import com.example.BookStoreApplication.user_dto.UserDTO;
import com.example.BookStoreApplication.user_model.User;
import com.example.BookStoreApplication.user_repository.UserRepository;
import com.example.BookStoreApplication.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtility tokenUtility;

    public UserDTO registerUser(User user) {
        user.setRegisteredDate(new Date());
        user.setUpdatedDate(new Date());
        userRepository.save(user);
        return convertToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDob(userDTO.getDob());
        user.setUpdatedDate(new Date());
        user.setEmailId(userDTO.getEmailId());
        user.setRole(userDTO.getRole());
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setDob(user.getDob());
        userDTO.setRegisteredDate(user.getRegisteredDate());
        userDTO.setUpdateDate(user.getUpdatedDate());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDob(userDTO.getDob());
        user.setEmailId(userDTO.getEmailId());
        user.setRole(userDTO.getRole());
        return user;
    }

    public String login(LoginDTO loginDTO) {
        String userEmail = loginDTO.getEmailId();
        String userPassword = loginDTO.getPassword();
        System.out.println(userEmail);
        System.out.println(userPassword);
        User check = userRepository.findByemailId(userEmail);
        System.out.println(check.getId());
        if (check != null) {
            if (userEmail.equals(check.getEmailId()) && (userPassword.equals(check.getPassword()))) {
                String token = tokenUtility.getToken(check.getId(), check.getRole());
                return token;
            }
            throw new AuthenticationException("User or password invalid!");
        } else {
            throw new AuthenticationException("User does not exist");
        }
    }

    public UserDTO getByToken(String token) {
        DataHolder dataHolder=tokenUtility.decode(token);
        return convertToDTO(userRepository.findById(dataHolder.getId()).orElseThrow(()->new RuntimeException("Id not found")));
    }
}
