package com.importKaran.assignments.module2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "department_module2")
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private LocalDate departmentCreationDate;
    private LocalDate departmentEndDate;
    private boolean isDepartmentActive;
    private String departmentURL;
    private String departmentCode;
}
