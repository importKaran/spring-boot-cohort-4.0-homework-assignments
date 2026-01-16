package com.importKaran.assignments.module2.h2DB.workingExample;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeRequest {

    private Long id;

    @NameValidation
    private String name;
    private Long age;
    @NotBlank
    private String email;
    private LocalDate joiningDate;
    private Boolean isActive;
}
