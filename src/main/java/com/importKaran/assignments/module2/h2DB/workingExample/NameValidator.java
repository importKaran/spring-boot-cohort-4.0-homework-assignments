package com.importKaran.assignments.module2.h2DB.workingExample;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<NameValidation, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

//        First check for null and empty
        if(s == null  ||  s.trim().isEmpty())
            return false;

//        And check whether the name is more than 3 characters, but less than 50 characters
        if(s.length() < 3  ||  s.length() > 50)
            return false;

//        Now check for numbers and special characters
//        Also, only one space between two words, like First Name + Last Name
//        And if all check passes, then return true, or else, return false
        return s.matches("^[a-zA-Z]+ [a-zA-Z]+$")  ||  s.matches("^[a-zA-Z]+$");
    }
}
