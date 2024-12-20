package edu.miu.cs544.moe.emr.helper;

import edu.miu.cs544.moe.emr.exception.HttpStatusException;
import edu.miu.cs544.moe.emr.shared.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpStatusException.class)
    public ResponseEntity<?> handleConflict(HttpStatusException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorResponse(ex.getHttpStatus().value(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(401).body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.status(400).body(new ErrorResponse(400, ex.getMostSpecificCause().getMessage()));
    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ErrorResponse handleBadCredentials(BadCredentialsException ex) {
//        return new ErrorResponse(401, ex.getMessage());
//    }
//
//    @ExceptionHandler(InternalAuthenticationServiceException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorResponse handleUsernameNotFound(InternalAuthenticationServiceException ex) {
//        return new ErrorResponse(404, ex.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResponse handleException(Exception ex) {
//        return new ErrorResponse(500, ex.getMessage());
//    }
//

}
