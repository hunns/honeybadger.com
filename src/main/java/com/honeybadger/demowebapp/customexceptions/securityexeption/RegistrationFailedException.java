package com.honeybadger.demowebapp.customexceptions.securityexeption;

import jakarta.validation.constraints.Email;

public class RegistrationFailedException extends RuntimeException {
    public RegistrationFailedException(String msg){
        super(msg);
    }

}
