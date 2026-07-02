package com.friend.cleanup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContactRequest(
        @NotBlank(message = "Введите имя")
        @Size(max = 80, message = "Имя слишком длинное")
        @NoSqlInjection
        String name,

        @NotBlank(message = "Введите телефон")
        @Pattern(regexp = "^\\+?\\d[\\d()\\-\\s]{6,22}\\d$", message = "Неверный формат телефона")
        @NoSqlInjection
        String phone,

        @Size(max = 600, message = "Описание слишком длинное")
        @NoSqlInjection
        String message
) {
}
