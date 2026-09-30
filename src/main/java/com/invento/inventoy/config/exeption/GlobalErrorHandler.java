package com.invento.inventoy.config.exeption;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response.ApiResponse<Void>> handleException(Exception ex){
        Response.ApiResponse<Void> response = new Response.ApiResponse<>(
                false,
                500,
                ex.getLocalizedMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response.ApiResponse<Void>> handleIllegalArgumentException(Exception ex){
        Response.ApiResponse<Void> response = new Response.ApiResponse<>(
                false,
                409,
                ex.getLocalizedMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Response.ApiResponse<Void>> handleAuthenticationException(Exception ex){
        Response.ApiResponse<Void> response = new Response.ApiResponse<>(
                false,
                401,
                ex.getLocalizedMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response.ApiResponse<Void>> handleAccessDeniedException(Exception ex){
        Response.ApiResponse<Void> response = new Response.ApiResponse<>(
                false,
                403,
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Response.ApiResponse<Void>> handleNoSuchElementException(Exception ex){
        Response.ApiResponse<Void> response = new Response.ApiResponse<>(
                false,
                404,
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Response.ApiResponse<Void>> handleIllegalStateException(Exception ex){
        Response.ApiResponse<Void> response = new Response.ApiResponse<>(
                false,
                400,
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Response.ApiResponse<Void>> handleDataIntegrityViolation(DataIntegrityViolationException e){
        Response.ApiResponse<Void> res = new Response.ApiResponse<>(
                false,
                409,
                e.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
    }

}
