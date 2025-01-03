package com.honeybadger.demowebapp.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRegistrationInput {

@NotNull(message = "First Name cannot be null/blank")
@NotEmpty(message = "First Name cannot be null/blank")
    private String  firstName;
    @NotNull(message = "Last Name cannot be null/blank")
    @NotEmpty(message = "Last Name cannot be null/blank")
    private String lastName;
    @Email(message = "Please enter the valid email id")
    private String email;
    @NotNull(message = "Please enter the valid email id" )
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;
    private String roles;
}
