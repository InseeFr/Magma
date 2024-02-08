package fr.insee.rmes.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RmesExceptionHandler {

    @ExceptionHandler(RmesException.class)
    public ResponseEntity<ErrorResponse> handleRmesException(RmesException ex) {
        ErrorResponse response = new ErrorResponse(ex.getStatus(), ex.getMessage(), ex.getDetails());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getStatus()));
    }
}


