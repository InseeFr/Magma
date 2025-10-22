package fr.insee.rmes.magma.gestion.old.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RmesExceptionHandler {

    @ExceptionHandler(RmesException.class)
    public ResponseEntity<RestMessage> handleRmesException(RmesException ex) {
        return new ResponseEntity<>(ex.toRestMessage(), HttpStatus.valueOf(ex.getStatus()));
    }
}


