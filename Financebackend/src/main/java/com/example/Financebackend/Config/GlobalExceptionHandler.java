package com.example.Financebackend.Config;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Financebackend.DTO.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {

        String message = ex.getMessage();

        if (message.startsWith("UNAUTHORIZED")) {
            return new ResponseEntity<>(
                    new ErrorResponse(message, 401),
                    HttpStatus.UNAUTHORIZED
            );
        }

        if (message.startsWith("FORBIDDEN")) {
            return new ResponseEntity<>(
                    new ErrorResponse(message, 403),
                    HttpStatus.FORBIDDEN
            );
        }

        if (message.startsWith("NOT_FOUND")) {
            return new ResponseEntity<>(
                    new ErrorResponse(message, 404),
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                new ErrorResponse(message, 400),
                HttpStatus.BAD_REQUEST
        );
    }
}