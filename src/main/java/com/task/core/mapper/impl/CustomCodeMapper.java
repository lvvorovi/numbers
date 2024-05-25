package com.task.core.mapper.impl;

import com.task.core.dto.CodeDto;
import com.task.core.entity.CodeEntity;
import com.task.core.mapper.CodeMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomCodeMapper implements CodeMapper {

    @Override
    public List<CodeEntity> toEntity(List<CodeDto> codeDtoList) {
        return codeDtoList.stream()
                .map(this::toEntity)
                .toList();
    }

    private CodeEntity toEntity(CodeDto dto) {
        return CodeEntity.builder()
                .withId(String.valueOf(dto.code()))
                .withCountryName(dto.countryName())
                .build();
    }
}
