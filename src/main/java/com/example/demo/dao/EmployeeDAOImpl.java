package com.example.demo.dao;

import com.example.demo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private final EntityManager em;

    public EmployeeDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public void save(Employee employee) {
        em.persist(employee);
    }

    @Override
    public Employee findById(int id) {
        return em.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = em.createQuery("select e from Employee e", Employee.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        Employee employeeDB = em.find(Employee.class, id);
        em.remove(employeeDB);
    }

    @Override
    public void update(Employee employee) {
        em.merge(employee);
    }
}
