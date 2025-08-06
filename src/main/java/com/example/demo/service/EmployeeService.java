package com.example.demo.service;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    void deleteById(int id);

    void update(Employee employee);

    void save(Employee employee);
}
