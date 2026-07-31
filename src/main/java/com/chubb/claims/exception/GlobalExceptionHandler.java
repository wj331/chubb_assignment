package com.chubb.claims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(body(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(
            DuplicateResourceException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(body(HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(InvalidClaimOperationException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidClaimOperation(
            InvalidClaimOperationException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(body(HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fe -> fieldErrors.put(
                        fe.getField(),
                        fe.getDefaultMessage()));

        Map<String, Object> b =
                body(HttpStatus.BAD_REQUEST, "Validation failed");

        b.put("fieldErrors", fieldErrors);

        return ResponseEntity.badRequest().body(b);
    }

    private Map<String, Object> body(
            HttpStatus status,
            String message) {

        Map<String, Object> b = new HashMap<>();

        b.put("timestamp", LocalDateTime.now());
        b.put("status", status.value());
        b.put("error", status.getReasonPhrase());
        b.put("message", message);

        return b;
    }
}
