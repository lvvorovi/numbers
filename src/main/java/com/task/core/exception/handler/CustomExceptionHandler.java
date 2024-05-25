package com.task.core.exception.handler;

import com.task.core.dto.ErrorDto;
import com.task.core.exception.NotANumberException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(NotANumberException.class)
    public ResponseEntity<ErrorDto> handleNotANumberException(NotANumberException ex, HttpServletRequest request) {
        log.warn(ex.getMessage());
        var errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, ex, request);

        return ResponseEntity
                .badRequest()
                .body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception ex, HttpServletRequest request) {
        log.error("Exception caught during request processing", ex);
        var errorDto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);

        return ResponseEntity
                .internalServerError()
                .body(errorDto);
    }

}
