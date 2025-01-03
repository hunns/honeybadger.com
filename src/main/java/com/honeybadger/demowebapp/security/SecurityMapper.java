package com.honeybadger.demowebapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class SecurityMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser userRegistrationInputToUser(UserRegistrationInput userRegistrationInput) {

        return AppUser.builder()
                .firstName(userRegistrationInput.getFirstName())
                .lastName(userRegistrationInput.getLastName())
                .email(userRegistrationInput.getEmail())
                .RegistrationId(String.valueOf(UUID.randomUUID()))
                .password(passwordEncoder.encode(userRegistrationInput.getPassword()))
                .roles(userRegistrationInput.getRoles())
                .build();
    }

    public MinUserDetail appUserToMinUserdetails(AppUser appUser) {
        return new MinUserDetail(
                appUser.getEmail(),
                appUser.getFirstName(),
                appUser.getLastName()
        );
    }
}