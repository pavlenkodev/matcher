package com.example.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
public class HomeController {

    @GetMapping("/")
    public String welcomePage(){
        return "welcome";
    }
}
