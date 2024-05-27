package com.task.core.validation.rule.impl;

import com.task.core.exception.NotANumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class NotANumberNumberValidationRuleTest {

    @InjectMocks
    private NotANumberNumberValidationRule victim;

    @Test
    void validate_whenIsANumber_thenDoNothing() {
        assertThatNoException().isThrownBy(() ->
                victim.validate("1230"));
    }

    @Test
    void validate_whenNull_thenDoNothing() {
        assertThatNoException().isThrownBy(() ->
                victim.validate(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123a", "123:", "123/", "123%", "123;"})
    void validate_whenContainsNotADigit_thenThrowNotANumberException(String inputValue) {
        assertThatThrownBy(() ->
                victim.validate(inputValue))
                .isInstanceOf(NotANumberException.class);
    }

}
