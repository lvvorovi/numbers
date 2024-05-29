package com.task.core.validation.rule.impl;

import com.task.core.exception.NotANumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class PositiveNumberValidationRuleTest {

    @InjectMocks
    private PositiveNumberValidationRule victim;

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"123A", "123,"})
    void validate_whenNotANumber_orZero_thenDoNothing(String input) {
        assertThatNoException().isThrownBy(() ->
                victim.validate(input));
    }

    @Test
    void validate_whenNumber_andGreatedThanZero_thenDoNothing() {
        assertThatNoException().isThrownBy(() ->
                victim.validate("1"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    void validate_whenZeroOrNegative_thenThrowNotANumberException(String input) {
        assertThatThrownBy(() ->
                victim.validate(input))
                .isInstanceOf(NotANumberException.class);
    }
}
