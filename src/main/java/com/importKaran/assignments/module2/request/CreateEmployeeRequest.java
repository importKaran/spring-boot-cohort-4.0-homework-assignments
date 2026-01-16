package com.importKaran.assignments.module2.request;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class CreateEmployeeRequest extends EmployeeRequest {

    @Null(message = "Since we are about to create a new Employee, the ID should be null!")
    private Integer id;

    @AssertFalse(message = "Employee should not be active by-default in case of creation of new Employee")
    private Boolean isEmployeeActive;
}
