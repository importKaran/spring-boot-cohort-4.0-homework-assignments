package com.importKaran.assignments.module2.controller;

import com.importKaran.assignments.module2.request.CreateDepartmentRequest;
import com.importKaran.assignments.module2.request.UpdateDepartmentRequest;
import com.importKaran.assignments.module2.response.DepartmentResponse;
import com.importKaran.assignments.module2.service.DepartmentBusinessLogic;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentPresentationLayer {

    @Autowired
    DepartmentBusinessLogic service;

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {
        return service.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable(value = "id") Integer departmentId) {
        return service.getDepartmentById(departmentId);
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> createNewDepartment(@RequestBody @Valid CreateDepartmentRequest request) {
        return service.createNewDepartment(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartmentPut(@PathVariable(value = "id") Integer departmentId,
                                    @RequestBody @Valid UpdateDepartmentRequest request) {
        return service.updateDepartmentPut(departmentId, request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartmentPatch(@PathVariable(value = "id") Integer departmentId,
                                                                    @RequestBody Map<String, Object> fieldsToUpdate) {
        return service.updateDepartmentPatch(departmentId, fieldsToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable(value = "id") Integer departmentId) {
        return service.deleteDepartment(departmentId);
    }
}
