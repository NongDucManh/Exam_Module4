package com.example.exammodule4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Employee Code cannot be blank!")
    private String employeeCode;
    @NotNull(message = "Name cannot be blank!")
    private String name;
    @NotNull(message = "Age cannot be blank!")
    @Min(18)
    @Max(60)
    private Integer age;
    @Pattern(regexp = "^[1-9]+[0-9]*$", message = "Salary must be a number!")
    private String salary;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
