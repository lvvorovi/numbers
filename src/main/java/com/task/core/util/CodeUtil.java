package com.task.core.util;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeUtil {

    public String sanitizeInputNumber(@NotNull String number) {
        return Optional.ofNullable(number)
                .map(this::sanitizeInternal)
                .orElse(null);
    }

    private String sanitizeInternal(String number) {
        while (number.startsWith("0") || number.startsWith("+")) {
            number = number.substring(1);
        }
        return number;
    }

}
