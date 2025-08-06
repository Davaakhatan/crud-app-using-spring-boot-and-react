package com.dave.employee_service.mapper;

import com.dave.employee_service.dto.EmployeeDto;
import com.dave.employee_service.model.Employee;

public class EmployeeMapper {
    public static EmployeeDto toDto(Employee emp) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(emp.getId());
        dto.setFirstName(emp.getFirstName());
        dto.setLastName(emp.getLastName());
        dto.setEmail(emp.getEmail());
        return dto;
    }
    public static Employee toEntity(EmployeeDto dto) {
        Employee emp = new Employee();
        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());
        return emp;
    }
}
