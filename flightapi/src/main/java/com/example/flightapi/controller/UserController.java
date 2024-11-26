package com.example.flightapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.service.UserService;
import com.example.flightapi.service.ApiResponse;
import com.example.flightapi.model.User; 

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> registerUser(@RequestBody User user) {
        ApiResponse response = userService.registerUser(user);
        
        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);  // Success: 201 Created
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);  // Failure: 400 Bad Request
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestParam String username, @RequestParam String passwordHash) {
        ApiResponse response = userService.loginUser(username, passwordHash);
        
        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);  // Success: 200 OK
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);  // Failure: 401 Unauthorized
        }
    }
}
