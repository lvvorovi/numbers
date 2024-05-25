package com.task.controller;

import com.task.core.dto.CodeDto;
import com.task.core.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/codes")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService service;

    @GetMapping(value = "/{number}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CodeDto> findCountryNameByNumber(@PathVariable String number) {
        return service.findByNumber(number)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }
}
