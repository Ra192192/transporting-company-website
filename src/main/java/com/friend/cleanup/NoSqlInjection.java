package com.friend.cleanup;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NoSqlInjectionValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoSqlInjection {
    String message() default "Поле содержит недопустимые символы или SQL-команду";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
