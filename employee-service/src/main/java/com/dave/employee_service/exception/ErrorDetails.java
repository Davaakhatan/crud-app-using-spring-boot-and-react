package com.dave.employee_service.exception;

import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Setter
public class ErrorDetails {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;
    private Map<String, List<String>> errors; // add setter if needed

    public ErrorDetails(LocalDateTime timestamp, int status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }
    // getters/setters
}
