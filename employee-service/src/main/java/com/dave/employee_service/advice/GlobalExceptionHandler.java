package com.dave.employee_service.advice;

import com.dave.employee_service.exception.ErrorDetails;
import com.dave.employee_service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleValidation(MethodArgumentNotValidException ex, WebRequest req) {
        Map<String, List<String>> errors = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            errors.computeIfAbsent(fe.getField(), key -> new ArrayList<>())
                    .add(fe.getDefaultMessage());
        }
        ErrorDetails details = new ErrorDetails(LocalDateTime.now(), 400,
                "Validation failed", req.getDescription(false));
        details.setErrors(errors); // if your ErrorDetails class supports it
        return details;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handleResourceNotFound(ResourceNotFoundException ex, WebRequest req) {
        return new ErrorDetails(LocalDateTime.now(), 404, ex.getMessage(), req.getDescription(false));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handleNoHandlerFound(NoHandlerFoundException ex, WebRequest req) {
        return new ErrorDetails(LocalDateTime.now(), 404, "Resource not found", req.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails handleAll(Exception ex, WebRequest req) {
        return new ErrorDetails(LocalDateTime.now(), 500, "Internal error", req.getDescription(false));
    }
}
