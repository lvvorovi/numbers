package com.task.core.validation;

import com.task.core.validation.rule.NumberValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeValidationService {

    private final List<NumberValidationRule> ruleList;

    public void validate(String number) {
        ruleList.forEach(rule -> rule.validate(number));
    }

}
