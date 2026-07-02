package com.friend.cleanup;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NoSqlInjectionValidator implements ConstraintValidator<NoSqlInjection, String> {
    private static final Pattern SUSPICIOUS_SQL = Pattern.compile(
            "(?i)(--|/\\*|\\*/|;|\\b(select|insert|update|delete|drop|alter|truncate|union|exec|execute|create|replace)\\b|\\bor\\s+\\d+\\s*=\\s*\\d+|\\band\\s+\\d+\\s*=\\s*\\d+)"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || !SUSPICIOUS_SQL.matcher(value).find();
    }
}
