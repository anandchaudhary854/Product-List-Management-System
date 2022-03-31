package com.example.productList.service;

import com.example.productList.controller.dto.AdminRegistrationDto;
import com.example.productList.controller.dto.UserRegistrationDto;
import com.example.productList.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
    User save(AdminRegistrationDto adminRegistrationDto);
    User save(User user);
    User loadUserByEmail(String username);
}
