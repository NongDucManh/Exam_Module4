package com.example.exammodule4.service.impl;

import com.example.exammodule4.model.Department;
import com.example.exammodule4.model.Employee;
import com.example.exammodule4.repository.IEmployeeRepository;
import com.example.exammodule4.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return iEmployeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findAllByName(Pageable pageable, String name) {
        return iEmployeeRepository.findAllByNameContaining(pageable, name);
    }

    @Override
    public Page<Employee> findAllAndSort(Pageable pageable, String s) {
        if(StringUtils.isEmpty(s)) {
            return iEmployeeRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "age")));
        }
        return iEmployeeRepository.findAllByNameContaining(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "age")), s);
    }

    @Override
    public Page<Employee> findAllByDepartment(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Employee> findAllByDepartment(Pageable pageable, Department department) {
        return null;
    }

    @Override
    public Iterable<Employee> findAll() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return iEmployeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) {
        iEmployeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        iEmployeeRepository.deleteById(id);
    }
}
