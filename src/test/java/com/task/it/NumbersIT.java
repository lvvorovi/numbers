package com.task.it;

import com.task.controller.CodeController;
import com.task.core.entity.CodeEntity;
import com.task.infra.repository.CodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class NumbersIT {

    public static final String TEST_COUNTRY_NAME = "TestCountryName";

    @Autowired
    private CodeRepository repository;
    @Autowired
    private CodeController controller;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void doFind_whenSaved() throws IOException {
        repository.save(CodeEntity.builder()
                .withId("123")
                .withCountryName(TEST_COUNTRY_NAME)
                .build());

        var result = controller.findCountryNameByNumber("1232356789");

        assertEquals(TEST_COUNTRY_NAME, result.getBody());
    }

}
