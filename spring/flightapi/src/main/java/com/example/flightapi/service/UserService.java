package com.example.flightapi.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightapi.model.DTO.UserDTO;
import com.example.flightapi.model.Entity.User;
import com.example.flightapi.repository.UserRepository;
import com.example.flightapi.util.JwtUtil;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * This function inserts or updates user to database
     */
    public ApiResponse registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            System.out.println("Username already taken: " + user.getUsername());
            return new ApiResponse(false, "Username is already taken.");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            System.out.println("Email already taken: " + user.getEmail());
            return new ApiResponse(false, "Email is already in use.");
        }

        userRepository.save(user);
        return new ApiResponse(true, "User registered successfully.");
    }

    public ApiResponse loginUser(String username, String passwordHash) {
        Optional<User> existingUser = userRepository.findByUsername(username);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            if (user.getPasswordHash().equals(passwordHash)) {
                String token = JwtUtil.generateToken(username);
                UserDTO userDTO = getUserDetails(username);

                if (userDTO == null) {
                    return new ApiResponse(false, "User details not found after successful login.", null);
                }

                Map<String, Object> responseData = new HashMap<>();
                responseData.put("token", token);
                responseData.put("user", userDTO);

                return new ApiResponse(true, "Login successful!", responseData);
            } else {
                return new ApiResponse(false, "Invalid password.");
            }
        }
        return new ApiResponse(false, "User not found.");
    }

    public UserDTO getUserDetails(String username) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        User user = existingUser.get();

        if (user == null) {
            System.out.println("User is null?? " + username);
            return null;
        }

        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }
}
