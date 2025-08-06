package com.example.demo.rest;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final ObjectMapper objectMapper;

    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee with id: " + employeeId + " not found");
        }
        return employee;
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @PutMapping("/employees")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee with id: " + employeeId + " not found");
        }
        employeeService.deleteById(employeeId);
    }

    @PatchMapping("/employees/{employeeId}")
    public void patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload) {
        Employee employee = employeeService.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee with id: " + employeeId + " not found");
        }

        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Can not update id");
        }

        Employee patchedEmployee = apply(patchPayload, employee);
        employeeService.update(patchedEmployee);

    }

    private Employee apply(Map<String, Object> patchPayload, Employee employee) {
        ObjectNode employeeNode = objectMapper.convertValue(employee, ObjectNode.class);
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);
        employeeNode.setAll(patchNode);
        return objectMapper.convertValue(employeeNode, Employee.class);
    }


}
