package com.task.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeUtil {

    public void sanitizeInputNumber(String number) {
        while (number.startsWith("0") || number.startsWith("+")) {
            number = number.substring(1, number.length() - 1);
        }
    }

}
