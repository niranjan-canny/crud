package com.example.newProject.controller;

import com.example.newProject.entity.*;
import com.example.newProject.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository empRepo;
    private final DepartmentRepository deptRepo;

    public EmployeeController(EmployeeRepository empRepo, DepartmentRepository deptRepo) {
        this.empRepo = empRepo;
        this.deptRepo = deptRepo;
    }

    @PostMapping
    public Employee create(@RequestBody Employee emp) {
        // If department exists, assign it
        if (emp.getDepartment() != null && emp.getDepartment().getId() != null) {
            Department dept = deptRepo.findById(emp.getDepartment().getId()).orElseThrow();
            emp.setDepartment(dept);
        }
        return empRepo.save(emp);
    }

    @GetMapping
    public List<Employee> getAll() {
        return empRepo.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return empRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee emp) {
        Employee existing = empRepo.findById(id).orElseThrow();
        existing.setName(emp.getName());
        existing.setEmail(emp.getEmail());
        if (emp.getDepartment() != null && emp.getDepartment().getId() != null) {
            Department dept = deptRepo.findById(emp.getDepartment().getId()).orElseThrow();
            existing.setDepartment(dept);
        }
        return empRepo.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        empRepo.deleteById(id);
    }
}
