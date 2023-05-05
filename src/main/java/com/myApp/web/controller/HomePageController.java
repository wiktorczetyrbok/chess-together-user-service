package com.myApp.web.controller;

import com.myApp.web.model.UserEntity;
import com.myApp.web.security.SecurityUtil;
import com.myApp.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class HomePageController {
    private UserService userService;
    @Autowired
    public HomePageController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("")
    public String showHomePage(Model model) throws IOException {
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        return "home";
    }
}
