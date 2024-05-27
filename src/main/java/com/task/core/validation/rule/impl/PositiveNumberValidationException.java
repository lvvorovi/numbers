package com.task.core.validation.rule.impl;

import com.task.core.exception.NotANumberException;
import com.task.core.validation.rule.NumberValidationRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Slf4j
public class PositiveNumberValidationException implements NumberValidationRule {

    @Override
    public void validate(String number) {
        if (NumberUtils.isParsable(number)) {
            var parsed = NumberUtils.createNumber(number);
            if (parsed.longValue() <= 0) {
                throw new NotANumberException("Supplied number is not a valid phone number: %s"
                        .formatted(number));
            }
        }
    }
}
