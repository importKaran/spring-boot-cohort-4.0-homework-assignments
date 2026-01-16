package com.importKaran.assignments.module2.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDepartmentCodeValidator.class)
public @interface UniqueDepartmentCode {

    String message() default "Department Code should be unique, while creating a new Department!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
