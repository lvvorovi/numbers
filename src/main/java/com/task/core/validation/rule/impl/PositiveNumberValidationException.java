package com.task.core.validation.rule.impl;

import com.task.core.exception.NotANumberException;
import com.task.core.validation.rule.NumberValidationRule;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class PositiveNumberValidationException implements NumberValidationRule {

    @Override
    public void validate(String number) {
        if (Long.parseLong(number) <= 0) {
            throw new NotANumberException("Supplied number is not a valid phone number: %s"
                    .formatted(number));
        }
    }
}
