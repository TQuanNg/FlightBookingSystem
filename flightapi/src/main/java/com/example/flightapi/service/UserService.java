package com.example.flightapi.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightapi.repository.UserRepository;
import com.example.flightapi.utils.JwtUtil;
import com.example.flightapi.model.User; 



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
            return new ApiResponse(false, "Username is already taken.");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
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

                return new ApiResponse(true, "Login successful!", Map.of("token", token));
            } else {
                return new ApiResponse(false, "Invalid password.");
            }
        }
        return new ApiResponse(false, "User not found.");
    }

}
