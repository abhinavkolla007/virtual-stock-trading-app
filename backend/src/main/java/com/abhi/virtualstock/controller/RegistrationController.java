package com.abhi.virtualstock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abhi.virtualstock.constants.Constants;
import com.abhi.virtualstock.model.User;
import com.abhi.virtualstock.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class RegistrationController {
    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody User user) {
        LOGGER.info("Registering new user: {}", user.getEmail());
        userService.registerNewUser(user);
        return ResponseEntity.status(201).build(); // Created
    }
}