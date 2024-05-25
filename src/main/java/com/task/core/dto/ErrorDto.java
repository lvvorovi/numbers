package com.task.core.dto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorDto(
        int code,
        String error,
        String message,
        LocalDateTime timestamp,
        String uri,
        String method
) {
    public ErrorDto(HttpStatus status, Exception ex, HttpServletRequest request) {
        this(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI(),
                request.getMethod()
        );
    }
}
