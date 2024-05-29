package com.task.core.validation;

import com.task.core.dto.CodeDto;
import com.task.core.exception.NoNameCodeException;
import com.task.core.validation.rule.impl.NotANumberNumberValidationRule;
import com.task.core.validation.rule.impl.PositiveNumberValidationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CodeValidationServiceTest {

    @Mock
    private NotANumberNumberValidationRule notANumberNumberValidationRule;
    @Mock
    private PositiveNumberValidationRule positiveNumberValidationRule;

    private CodeValidationService victim;

    @BeforeEach
    void setUp() {
        victim = new CodeValidationService(List.of(
                notANumberNumberValidationRule,
                positiveNumberValidationRule
        ));
    }

    @Test
    void validate_inputString() {
        var input = "inputString";

        doNothing().when(notANumberNumberValidationRule).validate(anyString());
        doNothing().when(positiveNumberValidationRule).validate(anyString());

        victim.validate(input);

        verify(notANumberNumberValidationRule).validate(input);
        verify(positiveNumberValidationRule).validate(input);
    }

    @Test
    void validate_codeDto_whenHasCountryName_thenDoNothing() {
        var codeDto = new CodeDto(123, "name");

        assertThatNoException().isThrownBy(() ->
                victim.validate(List.of(codeDto)));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = EMPTY)
    void validate_codeDto_whenHasNoCountryIsNullOrBlank_thenThrowNoNameCodeException(String input) {
        var codeDtoList = List.of(new CodeDto(123, input));

        assertThatThrownBy(() ->
                victim.validate(codeDtoList))
                .isInstanceOf(NoNameCodeException.class);
    }

}
