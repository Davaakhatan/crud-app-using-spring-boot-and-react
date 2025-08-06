package com.dave.employee_service.repository;

import com.dave.employee_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // CRUD methods available by default
}