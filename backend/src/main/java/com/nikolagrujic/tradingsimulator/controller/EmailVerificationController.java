package com.nikolagrujic.tradingsimulator.controller;

import com.nikolagrujic.tradingsimulator.exception.ExpiredTokenException;
import com.nikolagrujic.tradingsimulator.exception.InvalidTokenException;
import com.nikolagrujic.tradingsimulator.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class EmailVerificationController {

    private final UserService userService;

    public EmailVerificationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token) {
        try {
            // Use the existing verification logic in UserService
            userService.verifyUser(token);

            return ResponseEntity.ok("{ \"success\": true }");

        } catch (ExpiredTokenException e) {
            return ResponseEntity.badRequest()
                    .body("{ \"errorMessage\": \"Token expired. A new token has been sent.\" }");

        } catch (InvalidTokenException e) {
            return ResponseEntity.badRequest()
                    .body("{ \"errorMessage\": \"Invalid token\" }");
        }
    }
}
