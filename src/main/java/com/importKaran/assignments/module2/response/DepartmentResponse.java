package com.importKaran.assignments.module2.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DepartmentResponse {

    private Integer id;
    private String departmentCode;
    private String title;
    private LocalDate departmentCreationDate;
    private LocalDate departmentEndDate;
    private Boolean isDepartmentActive;
    private String departmentURL;
}
