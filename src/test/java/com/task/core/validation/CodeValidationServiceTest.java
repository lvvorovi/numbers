package com.task.core.validation;

import com.task.core.validation.rule.impl.NotANumberNumberValidationRule;
import com.task.core.validation.rule.impl.PositiveNumberValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CodeValidationServiceTest {

    @Mock
    private NotANumberNumberValidationRule notANumberNumberValidationRule;
    @Mock
    private PositiveNumberValidationException positiveNumberValidationException;

    private CodeValidationService victim;

    @BeforeEach
    void setUp() {
        victim = new CodeValidationService(List.of(
                notANumberNumberValidationRule,
                positiveNumberValidationException
        ));
    }

    @Test
    void validate() {
        var input = "inputString";

        doNothing().when(notANumberNumberValidationRule).validate(anyString());
        doNothing().when(positiveNumberValidationException).validate(anyString());

        victim.validate(input);

        verify(notANumberNumberValidationRule).validate(input);
        verify(positiveNumberValidationException).validate(input);
    }

}
