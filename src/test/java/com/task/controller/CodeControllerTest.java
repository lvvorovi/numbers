package com.task.controller;

import com.task.core.dto.CodeDto;
import com.task.core.service.CodeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CodeControllerTest {

    @Mock
    private CodeService service;

    @InjectMocks
    private CodeController victim;

    @Test
    void findCountryNameByNumber_whenFound_thenReturn200() {
        var dto = new CodeDto(123, "name");

        when(service.findByNumber("number")).thenReturn(Optional.of(dto));

        var result = victim.findCountryNameByNumber("number");

        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
    }

    @Test
    void findCountryNameByNumber_whenNotFound_thenReturn204() {
        when(service.findByNumber("number")).thenReturn(Optional.empty());

        var result = victim.findCountryNameByNumber("number");

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatusCode().value());
    }
}
