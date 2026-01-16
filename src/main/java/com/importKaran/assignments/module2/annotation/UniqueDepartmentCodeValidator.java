package com.importKaran.assignments.module2.annotation;

import com.importKaran.assignments.module2.entity.Department;
import com.importKaran.assignments.module2.repository.DepartmentRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UniqueDepartmentCodeValidator implements ConstraintValidator<UniqueDepartmentCode, String> {

    @Autowired
    DepartmentRepo repo;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        List<Department> departments = repo.findByDepartmentCode(s);

//        If we find any department with the same department code, then we should not allow that
        return (departments == null  ||  departments.isEmpty());
    }
}
