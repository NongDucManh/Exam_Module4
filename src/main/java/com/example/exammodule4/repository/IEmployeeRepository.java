package com.example.exammodule4.repository;

import com.example.exammodule4.model.Department;
import com.example.exammodule4.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    Page<Employee> findAllByNameContaining(Pageable pageable, String name);
    Page<Employee> findAllByDepartment(Pageable pageable, Department department);
}
