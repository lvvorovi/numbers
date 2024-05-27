package com.task.core.dto;

public record ResponseDto(
        String countryName
) {
    public ResponseDto(CodeDto codeDto) {
        this(codeDto.countryName());
    }
}
