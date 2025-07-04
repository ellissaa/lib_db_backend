package org.example.database_lib.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        String message = ex.getMessage();
        if (ex instanceof DataIntegrityViolationException) {
            message = "Нарушена целостность данных\n" + message;
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}