package com.task.core.exception.handler;

import com.task.core.exception.NotANumberException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomExceptionHandlerTest {

    @InjectMocks
    private CustomExceptionHandler victim;

    @Test
    void handleNotANumberException() {
        var request = mock(HttpServletRequest.class);
        when(request.getMethod()).thenReturn("method");
        when(request.getRequestURI()).thenReturn("requestUri");

        var result = victim.handleNotANumberException(new NotANumberException("message"), request);

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatusCode().value());
        assertEquals("message", result.getBody().message());
    }

    @Test
    void handleException() {
        var request = mock(HttpServletRequest.class);
        when(request.getMethod()).thenReturn("method");
        when(request.getRequestURI()).thenReturn("requestUri");

        var result = victim.handleException(new RuntimeException("message"), request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getStatusCode().value());
        assertEquals("message", result.getBody().message());
    }

}
