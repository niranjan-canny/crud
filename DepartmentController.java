package com.example.newProject.controller;

import com.example.newProject.entity.Department;
import com.example.newProject.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepo;

    public DepartmentController(DepartmentRepository departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @PostMapping
    public Department create(@RequestBody Department dept) {
        return departmentRepo.save(dept);
    }

    @GetMapping
    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return departmentRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department dept) {
        Department existing = departmentRepo.findById(id).orElseThrow();
        existing.setName(dept.getName());
        return departmentRepo.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentRepo.deleteById(id);
    }
}
