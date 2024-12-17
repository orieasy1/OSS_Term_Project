package com.team3.ossproject.user.controller;

import com.team3.ossproject.user.dto.AddUserRequest;
import com.team3.ossproject.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name= "User", description = "User Signup API")
@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    @Operation(summary = "Sign up page", description = "[View] Display the sign-up page for new users")
    public String signup() {
        return "signup";
    }

    @PostMapping
    @Operation(summary = "User Registration", description = "Process user registration and save user information.")
    public String register(AddUserRequest request) {
        userService.save(request);
        return "redirect:/api/v1/auth/oauth2/google";

    }

}