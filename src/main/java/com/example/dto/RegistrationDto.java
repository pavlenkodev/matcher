package com.example.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
}
