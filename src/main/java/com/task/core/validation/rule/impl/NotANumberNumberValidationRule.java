package com.task.core.validation.rule.impl;

import com.task.core.exception.NotANumberException;
import com.task.core.validation.rule.NumberValidationRule;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(0)
public class NotANumberNumberValidationRule implements NumberValidationRule {

    @Override
    public void validate(String number) {
        Optional.ofNullable(number)
                .filter(s -> !NumberUtils.isParsable(s))
                .ifPresent(notANumber -> {
                    throw new NotANumberException("Requested number is not a valid phone number: %s"
                            .formatted(number));
                });

    }
}
