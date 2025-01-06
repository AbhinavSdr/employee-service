package com.demo.employee_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employee_service.dao.EmployeeRepository;
import com.demo.employee_service.dao.entity.Employee;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepo;

    public List<Employee> getEmployeesByDepartment(int deptId)
    {
        return empRepo.findByEmpDeptId(deptId);
    }

    public List<Employee> getAllEmployee(){
        return empRepo.findAll();
    }

    public Optional<Employee> getAEmployee(int deptId)
    {
        return empRepo.findById(deptId);
    }

    public Employee addEmployee(Employee newDept)
    {
        return empRepo.saveAndFlush(newDept);
    }

    public Employee updateEmployee(Employee updateDept)
    {
        return empRepo.save(updateDept);
    }

    public void deleteEmployee(int deptId)
    {
        empRepo.deleteById(deptId);
    } 
}
