package com.task.core.validation.rule.impl;

import com.task.core.exception.NotANumberException;
import com.task.core.validation.rule.NumberValidationRule;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
public class NotANumberNumberValidationRule implements NumberValidationRule {

    @Override
    public void validate(String number) {
        try {
            Long.valueOf(number);
        } catch (NumberFormatException ex) {
            throw new NotANumberException("Requested number is not a valid phone number: %s"
                    .formatted(number));
        }
    }
}
