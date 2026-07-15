package com.app.dto;

import com.app.validation.NoSqlInjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContactRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 80, message = "Name is too long")
        @NoSqlInjection
        String name,

        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "^\\+?\\d[\\d()\\-\\s]{6,22}\\d$", message = "Invalid phone format")
        @NoSqlInjection
        String phone,

        @Size(max = 600, message = "Description is too long")
        @NoSqlInjection
        String message
) {
}
