package com.example.productList.controller;

import com.example.productList.controller.dto.AdminRegistrationDto;
import com.example.productList.controller.dto.UserRegistrationDto;
import com.example.productList.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class AdminRegistrationController {

    private UserService userService;
    public AdminRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public AdminRegistrationDto adminRegistrationDto() {
        return new AdminRegistrationDto();
    }

    @GetMapping("/adminRegistration")
    public String AdminRegistrationForm(){
        return "adminRegistration";
    }


    @PostMapping("/adminRegistration")
    public String registerAdminAccount(@ModelAttribute("user")AdminRegistrationDto adminRegistrationDto){
        userService.save(adminRegistrationDto);
        return "redirect:/adminRegistration?success";
    }
}
