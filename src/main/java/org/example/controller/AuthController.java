package org.example.controller;

import org.example.dto.AuthResponse;
import org.example.dto.LoginRequest;
import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.service.AuthService;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService,
                          UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request) {
        return userService.saveUser(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}