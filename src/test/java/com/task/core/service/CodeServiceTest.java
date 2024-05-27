package com.task.core.service;

import com.task.core.dto.CodeDto;
import com.task.core.entity.CodeEntity;
import com.task.core.mapper.CodeMapper;
import com.task.core.util.CodeUtil;
import com.task.core.validation.CodeValidationService;
import com.task.infra.repository.CodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CodeServiceTest {

    @Mock
    private CodeRepository repository;
    @Mock
    private CodeMapper mapper;
    @Mock
    private CodeUtil util;
    @Mock
    private CodeValidationService validationService;

    @InjectMocks
    private CodeService victim;


    @Test
    void findByNumber_whenFoundInDB_thenSort_andReturn() {
        var inputNumber = "1234567";
        var codeDtoA = new CodeDto(123, "expectedName");
        var codeDtoB = new CodeDto(12, "notExpectedName");

        doNothing().when(validationService).validate(inputNumber);
        when(util.sanitizeInputNumber(inputNumber)).thenReturn(inputNumber);
        when(repository.findByIdStartsWith(inputNumber.substring(0,1)))
                .thenReturn(Stream.of(codeDtoB, codeDtoA));

        var result = victim.findByNumber(inputNumber);

        assertTrue(result.isPresent());
        assertEquals("expectedName", result.get().countryName());
    }

    @Test
    void findByNumber_whenNotFoundInDB_returnOptionalEmpty() {
        doNothing().when(validationService).validate(anyString());
        when(util.sanitizeInputNumber(anyString())).thenReturn("anyString");
        when(repository.findByIdStartsWith(anyString())).thenReturn(Stream.of());

        var result = victim.findByNumber("anyString");

        assertThat(result)
                .isEmpty();
    }

    @Test
    void saveAll_whenProvided_thenPassToRepositoryToSave() {
        var dto = new CodeDto(123, "name");
        var entity = new CodeEntity("123", "name");
        var savedEntity = new CodeEntity("123", "savedName");

        doNothing().when(validationService).validate(List.of(dto));
        when(mapper.toEntity(List.of(dto))).thenReturn(List.of(entity));
        when(repository.saveAll(List.of(entity))).thenReturn(List.of(savedEntity));

        victim.saveAll(List.of(dto));

        verifyNoMoreInteractions(validationService, mapper, repository);
    }

    @Test
    void deleteAll_whenCalled_delegateToRepository() {
        victim.deleteAll();

        verify(repository).deleteAll();
    }


}
