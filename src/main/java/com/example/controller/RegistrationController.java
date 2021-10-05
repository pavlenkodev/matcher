package com.example.controller;

import com.example.dto.RegistrationDto;
import com.example.model.User;
import com.example.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final UserServiceImpl userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration-view";
    }

    @PostMapping("/registration")
    public String registerUser(RegistrationDto user) {
        Optional<User> userFromDb = userService.findByUsername(user.getUsername());
        if (!userFromDb.isPresent()) {
            userService.register(user);
        }
        return "redirect:/login";
    }
}
