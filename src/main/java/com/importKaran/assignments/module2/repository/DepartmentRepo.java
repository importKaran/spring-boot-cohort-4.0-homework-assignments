package com.importKaran.assignments.module2.repository;

import com.importKaran.assignments.module2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

    List<Department> findByDepartmentCode(String departmentCode);
}
