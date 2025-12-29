package com.importKaran.assignments.module2.h2DB.workingExample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

//    Create a new employee
    @PostMapping("/create-employee")
    public EmployeeResponse createNewEmployee(@RequestBody EmployeeRequest requestBody) {
        return service.createNewEmployee(requestBody);
    }

//    Fetch an employee with ID
    @GetMapping("/fetch/{employeeId}")
    public EmployeeResponse fetchEmployeeById(@PathVariable(value = "employeeId") Long id) {
        return service.fetchEmployeeById(id);
    }

//    Fetch all employees
    @GetMapping("/fetch/all")
    public List<EmployeeResponse> fetchAllEmployees() {
        return service.fetchAllEmployees();
    }

//    Update an employee record - PUT
    @PutMapping("/update")
    public EmployeeResponse updateEmployeeByPut(@RequestBody EmployeeRequest requestBody) {
        return service.updateEmployeeByPut(requestBody);
    }

//    Update an employee record - PATCH
    @PatchMapping("/update")
    public EmployeeResponse updateEmployeeByPatch(@RequestBody EmployeeRequest requestBody) {
        return service.updateEmployeeByPatch(requestBody);
    }

//    Delete employee by ID
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(value = "employeeId") Long id) {
        return service.deleteEmployee(id);
    }

}
