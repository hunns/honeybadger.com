package com.honeybadger.demowebapp.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("operation")
public class MethodLevelAuthenticationController {

    @GetMapping("/admin-page")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> getAdminAccessPage(){
        return ResponseEntity.ok("This is admin page and will have only access to admin");
    }


    @GetMapping("/manager-page")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    private ResponseEntity<?> getManagerAccessPage(){
        return ResponseEntity.ok("This is manager page and will have only access to admin");
    }


    @GetMapping("/user-page")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    private ResponseEntity<?> getUserAccessPage(){
        return ResponseEntity.ok("This is user page and will have only access to admin");
    }



}
