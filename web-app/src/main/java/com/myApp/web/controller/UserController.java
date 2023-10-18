package com.myApp.web.controller;

import com.myApp.web.dto.UserDto;
import com.myApp.web.model.UserEntity;
import com.myApp.web.security.SecurityUtil;
import com.myApp.web.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit-form")
    public ResponseEntity<UserEntity> editUserForm() {
        String username = SecurityUtil.getSessionUser();
        Long userId = userService.findByUsername(username).getId();
        Optional<UserEntity> user = userService.findByUserId(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }

        String username = SecurityUtil.getSessionUser();
        Long userId = userService.findByUsername(username).getId();
        user.setId(userId);
        userService.updateUser(user);

        return ResponseEntity.ok("User updated successfully");
    }
}