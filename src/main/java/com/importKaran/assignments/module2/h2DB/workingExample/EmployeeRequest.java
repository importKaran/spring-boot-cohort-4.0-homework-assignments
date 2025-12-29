package com.importKaran.assignments.module2.h2DB.workingExample;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeRequest {

    private Long id;
    private String name;
    private Long age;
    private String email;
    private LocalDate joiningDate;
    private Boolean isActive;
}
