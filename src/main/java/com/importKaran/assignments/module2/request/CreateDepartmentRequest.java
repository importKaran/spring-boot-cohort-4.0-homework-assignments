package com.importKaran.assignments.module2.request;

import com.importKaran.assignments.module2.annotation.UniqueDepartmentCode;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class CreateDepartmentRequest extends DepartmentRequest {

    @Null(message = "Since we are about to create a new Department, the ID should be null!")
    private Integer id;

    @UniqueDepartmentCode
    private String departmentCode;
}
