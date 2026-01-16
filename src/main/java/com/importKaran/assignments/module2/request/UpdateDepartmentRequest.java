package com.importKaran.assignments.module2.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateDepartmentRequest extends DepartmentRequest {

    @NotNull(message = "Since we are about to update an existing Department, the ID can't be null!")
    private Integer id;

    private String departmentCode;
}
