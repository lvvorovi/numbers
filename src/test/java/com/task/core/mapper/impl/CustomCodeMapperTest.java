package com.task.core.mapper.impl;

import com.task.core.dto.CodeDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CustomCodeMapperTest {

    @InjectMocks
    private CustomCodeMapper victim;

    @Test
    void toEntity() {
        var dtoMock = new CodeDto(123, "name");

        var result = victim.toEntity(List.of(dtoMock));

        assertThat(result)
                .isNotNull()
                .hasSize(1);

        assertEquals(String.valueOf(dtoMock.code()), result.get(0).getId());
        assertEquals(dtoMock.countryName(), result.get(0).getCountryName());
    }

}
