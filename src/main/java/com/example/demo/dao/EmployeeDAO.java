package com.example.demo.dao;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    void save(Employee employee);

    Employee findById(int id);

    List<Employee> findAll();

    void deleteById(int id);

    void update(Employee employee);
}
