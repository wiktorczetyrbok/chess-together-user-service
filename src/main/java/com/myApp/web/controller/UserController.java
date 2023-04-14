package com.myApp.web.controller;

import com.myApp.web.dto.ClubDto;
import com.myApp.web.dto.UserDto;
import com.myApp.web.model.UserEntity;
import com.myApp.web.repository.UserRepository;
import com.myApp.web.security.SecurityUtil;
import com.myApp.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users/edit")
    public String editUserForm(Model model) {
        String username = SecurityUtil.getSessionUser();
        Long userId = userService.findByUsername(username).getId();
        Optional<UserEntity> user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        return "user-edit";
    }
    @PostMapping("/user/edit")
    public String updateUser(@Valid @ModelAttribute("user") UserDto user, BindingResult result) {
        if(result.hasErrors()){
            return "user-edit";
        }
        String username = SecurityUtil.getSessionUser();
        Long userId = userService.findByUsername(username).getId();
        user.setId(userId);
        userService.updateUser(user);
        return "redirect:";
    }
}
