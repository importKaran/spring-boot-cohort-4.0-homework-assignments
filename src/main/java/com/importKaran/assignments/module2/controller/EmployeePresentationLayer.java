package com.importKaran.assignments.module2.controller;

import com.importKaran.assignments.module2.request.CreateEmployeeRequest;
import com.importKaran.assignments.module2.request.UpdateEmployeeRequest;
import com.importKaran.assignments.module2.response.EmployeeResponse;
import com.importKaran.assignments.module2.service.EmployeeBusinessLogic;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeePresentationLayer {

    @Autowired
    EmployeeBusinessLogic service;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable(value = "id") Integer employeeId) {
        return service.getEmployeeById(employeeId);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createNewEmployee(@RequestBody @Valid CreateEmployeeRequest request) {
        return service.createNewEmployee(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployeePut(@PathVariable(value = "id") Integer employeeId,
                                                                  @RequestBody @Valid UpdateEmployeeRequest request) {
        return service.updateEmployeePut(employeeId, request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployeePatch(@PathVariable(value = "id") Integer employeeId,
                                                                    @RequestBody Map<String, Object> fieldsToUpdate) {
        return service.updateEmployeePatch(employeeId, fieldsToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") Integer employeeId) {
        return service.deleteEmployee(employeeId);
    }
}
