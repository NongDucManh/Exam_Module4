package com.example.exammodule4.repository;

import com.example.exammodule4.model.Department;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IDepartmentRepository extends PagingAndSortingRepository<Department, Long> {
}
