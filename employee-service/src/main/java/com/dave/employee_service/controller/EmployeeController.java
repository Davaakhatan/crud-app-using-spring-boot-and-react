package com.dave.employee_service.controller;

import com.dave.employee_service.dto.EmployeeDto;
import com.dave.employee_service.mapper.EmployeeMapper;
import com.dave.employee_service.model.Employee;
import com.dave.employee_service.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) { this.service = service; }

    @GetMapping
    public List<EmployeeDto> listAll() {
        return service.getAllEmployees().stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable Long id) {
        Employee emp = service.getEmployeeById(id);
        return EmployeeMapper.toDto(emp);
    }

    @PostMapping
    public EmployeeDto create(@Valid @RequestBody EmployeeDto dto) {
        Employee emp = EmployeeMapper.toEntity(dto);
        Employee saved = service.saveEmployee(emp);
        return EmployeeMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public EmployeeDto update(@Valid @PathVariable Long id, @RequestBody EmployeeDto dto) {
        Employee emp = EmployeeMapper.toEntity(dto);
        Employee updated = service.updateEmployee(id, emp);
        return EmployeeMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}


