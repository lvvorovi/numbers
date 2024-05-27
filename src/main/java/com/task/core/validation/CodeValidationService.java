package com.task.core.validation;

import com.task.core.dto.CodeDto;
import com.task.core.exception.NoNameCodeException;
import com.task.core.validation.rule.NumberValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeValidationService {

    private final List<NumberValidationRule> ruleList;

    public void validate(String number) {
        ruleList.forEach(rule -> rule.validate(number));
    }

    public void validate(List<CodeDto> codeDtoList) {
        codeDtoList.forEach(dto -> {
            if (!StringUtils.hasText(dto.countryName())) {
                throw new NoNameCodeException("cannot save code %s with no name supplied"
                        .formatted(dto));
            }
        });
    }

}
