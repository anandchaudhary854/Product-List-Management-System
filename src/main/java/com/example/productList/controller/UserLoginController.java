package com.example.productList.controller;

import com.example.productList.model.User;
import com.example.productList.service.UserService;
import com.example.productList.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserLoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/")
    public String home(Principal principal, Model model) {

        String username = principal.getName();

        User user = userService.loadUserByEmail(username);
        model.addAttribute("user", user);

        return "userProfile";
    }
}
