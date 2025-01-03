package com.honeybadger.demowebapp.security;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("registration")
@RestController

public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register-user")
    public ResponseEntity<?> RegisterNewUser( @Valid @RequestBody UserRegistrationInput userRegistrationInput) throws Exception {
        MinUserDetail savedAppUser = registrationService.registerNewUser(userRegistrationInput);
        return ResponseEntity.ok(savedAppUser);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?>  getSecureToken(@Valid @RequestBody AuthenticationInput authenticationInput){
        String token =registrationService.Authenticate(authenticationInput);
        return ResponseEntity.ok(token);
    }

}
