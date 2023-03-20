package com.example.exammodule4.service.impl;

import com.example.exammodule4.model.Department;
import com.example.exammodule4.repository.IDepartmentRepository;
import com.example.exammodule4.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    private IDepartmentRepository iDepartmentRepository;

    @Override
    public Iterable<Department> findAll() {
        return iDepartmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Long id) {
        return iDepartmentRepository.findById(id);
    }

    @Override
    public void save(Department department) {
        iDepartmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        iDepartmentRepository.deleteById(id);
    }
}
