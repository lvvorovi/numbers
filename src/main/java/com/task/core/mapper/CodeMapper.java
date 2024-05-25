package com.task.core.mapper;

import com.task.core.dto.CodeDto;
import com.task.core.entity.CodeEntity;

import java.util.List;

public interface CodeMapper {
    List<CodeEntity> toEntity(List<CodeDto> codeDtoList);

}
