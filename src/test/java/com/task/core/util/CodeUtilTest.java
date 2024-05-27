package com.task.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CodeUtilTest {

    @InjectMocks
    private CodeUtil victim;

    @Test
    void sanitizeInputNumber_whenNull_doNothing() {
        var result = victim.sanitizeInputNumber(null);

        assertThat(result)
                .isNull();
    }

    @Test
    void sanitizeInputNumber_whenDoesNotStart_thenDoNonthing() {
        var input = "hasNoZerosOrPlus";
        var inputHash = input.hashCode();

        victim.sanitizeInputNumber(input);

        assertEquals(inputHash, input.hashCode());
    }

    @Test
    void sanitizeInputNumber_whenStartsWith_thenSanitize() {
        var expected = "inputString";
        var input = "00++".concat(expected);

        var result = victim.sanitizeInputNumber(input);

        assertEquals(expected, result);
    }

}
