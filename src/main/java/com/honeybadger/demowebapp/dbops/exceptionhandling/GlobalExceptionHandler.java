package com.honeybadger.demowebapp.dbops.exceptionhandling;

import java.util.HashSet;
import java.util.Set;

import com.honeybadger.demowebapp.customexceptions.securityexeption.RegistrationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.honeybadger.demowebapp.customexceptions.schoolException.SchoolNotFoundException;
import com.honeybadger.demowebapp.customexceptions.studentException.StudentNotFoundException;

import static com.honeybadger.demowebapp.dbops.exceptionhandling.CustomErrorLibrary.UNABLE_TO_SAVE;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    ResponseEntity<Object> handleStudentNotFoundexception(StudentNotFoundException exp, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .businessErrorCode(UNABLE_TO_SAVE.getErrorCode())
                        .error(UNABLE_TO_SAVE.getErrorMessage())
                        .build());
    }

    @ExceptionHandler(RegistrationFailedException.class)
    ResponseEntity<Object> handleRegistrationFailedException(RegistrationFailedException exp, WebRequest request) {
        return ResponseEntity
                .status(UNABLE_TO_SAVE.getHttpStatus())
                .body(ExceptionResponse.builder()
                        .businessErrorCode(UNABLE_TO_SAVE.getErrorCode())
                        .error(UNABLE_TO_SAVE.getErrorMessage() + " due to " + exp.getMessage())
                        .build());
    }

    @ExceptionHandler(SchoolNotFoundException.class)
    ResponseEntity<ExceptionResponse> handSchoolNotFoundException(SchoolNotFoundException exp, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .businessErrorCode(CustomErrorLibrary.NOT_FOUND.getErrorCode())
                        .error(exp.getMessage())
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Set<String> errors = new HashSet<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .validationErrors(errors)
                                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exp) {
        exp.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorDescription("Internal error, please contact the admin")
                                .error(exp.getMessage())
                                .build());
    }

}
