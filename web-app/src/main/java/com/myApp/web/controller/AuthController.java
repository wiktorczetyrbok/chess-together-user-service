package com.myApp.web.controller;

import com.myApp.web.dto.RegistrationDto;
import com.myApp.web.model.UserEntity;
import com.myApp.web.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginPageInfo() {
        //TODO: Adjust this URL as needed
        String loginPageUrl = "/login";
        return ResponseEntity.ok(loginPageUrl);
    }

    @GetMapping("/register")
    public ResponseEntity<RegistrationDto> getRegisterForm() {
        RegistrationDto user = new RegistrationDto();
        return ResponseEntity.ok(user);
    }

    @PostMapping("register/save")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationDto user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }

        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());

        if (existingUserEmail != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        if (existingUserUsername != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        userService.saveUser(user);
        return ResponseEntity.ok("Registration successful");
    }
}