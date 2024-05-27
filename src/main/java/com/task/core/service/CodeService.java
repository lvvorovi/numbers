package com.task.core.service;

import com.task.core.dto.CodeDto;
import com.task.core.mapper.CodeMapper;
import com.task.core.util.CodeUtil;
import com.task.core.validation.CodeValidationService;
import com.task.infra.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository repository;
    private final CodeMapper mapper;
    private final CodeUtil util;
    private final CodeValidationService validationService;

    @Transactional(readOnly = true)
    public Optional<CodeDto> findByNumber(String number) {
        validationService.validate(number);
        var sanitizedNumber = util.sanitizeInputNumber(number);

        return repository.findByIdStartsWith(sanitizedNumber.substring(0, 1))
                .sorted(Comparator.comparing(CodeDto::code).reversed())
                .filter(dto -> sanitizedNumber.startsWith(String.valueOf(dto.code())))
                .findFirst()
                .map(dto -> {
                    log.info("CodeEntity found for number:{}", number);
                    return dto;
                });
    }

    public void saveAll(List<CodeDto> codeDtoList) {
        validationService.validate(codeDtoList);
        var entityList = mapper.toEntity(codeDtoList);
        var savedEntityList = repository.saveAll(entityList);
        log.info("{} CodeEntity saved", savedEntityList.size());
    }

    public void deleteAll() {
        repository.deleteAll();
        log.info("All CodeEntity deleted");
    }
}
