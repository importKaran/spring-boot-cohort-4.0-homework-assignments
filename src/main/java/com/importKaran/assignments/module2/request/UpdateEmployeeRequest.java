package com.importKaran.assignments.module2.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEmployeeRequest extends EmployeeRequest {

    @NotNull(message = "Since we are about to update an existing Employee, the ID can't be null!")
    private Integer id;

    @AssertTrue(message = "Employee should be active, in case of updating an existing Employee!")
    private Boolean isEmployeeActive;
}
