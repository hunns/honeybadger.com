package com.honeybadger.demowebapp.customexceptions.schoolException;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class SchoolNotFoundException extends RuntimeException {

    public SchoolNotFoundException(String msg) {
        super(msg);
    }

}
