package com.honeybadger.demowebapp.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manager")
public class ManagerController {

    @GetMapping
    private ResponseEntity<?> showMessage(){
        return ResponseEntity.ok("Manager Get called");

    }
}
