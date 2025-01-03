package com.honeybadger.demowebapp.dbops.exceptionhandling;

import org.springframework.http.HttpStatus;
import lombok.Getter;
public enum CustomErrorLibrary {
    NO_CODE(0,HttpStatus.NOT_IMPLEMENTED,"Error yet to define"),
    NOT_FOUND(-1,HttpStatus.NOT_FOUND,"Requested data not found"),
    UNABLE_TO_SAVE(-2,HttpStatus.BAD_REQUEST, "Save operation failed" ),
    ;
    
    @Getter
    private  final int errorCode;
    
    @Getter
    private final String errorMessage;
    
    @Getter
    private final HttpStatus httpStatus;


    CustomErrorLibrary(int errorCode, HttpStatus status, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = status;
    }


}
