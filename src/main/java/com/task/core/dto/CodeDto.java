package com.task.core.dto;

import com.task.core.entity.CodeEntity;

public record CodeDto(
        int code,
        String countryName
) {

    public CodeDto(CodeEntity codeEntity) {
        this(
                Integer.parseInt(codeEntity.getId()),
                codeEntity.getCountryName()
        );
    }
}
