package com.importKaran.assignments.module2.h2DB.workingExample;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
public @interface NameValidation {
    String message() default "Employee.name can't be blank, less than 3 characters, more than 50 characters, and no " +
            "special characters!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
