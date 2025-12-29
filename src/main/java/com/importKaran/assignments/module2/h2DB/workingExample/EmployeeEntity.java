package com.importKaran.assignments.module2.h2DB.workingExample;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long age;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate joiningDate;
    private Boolean isActive;
}
