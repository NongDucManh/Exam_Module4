package com.example.exammodule4.service;

import com.example.exammodule4.model.Department;
import com.example.exammodule4.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService extends IGeneralService<Employee> {
    Page<Employee> findAll(Pageable pageable);
    Page<Employee> findAllByName(Pageable pageable, String s);

    Page<Employee> findAllAndSort(Pageable pageable, String s);

    Page<Employee> findAllByDepartment(Pageable pageable, String name);
}
