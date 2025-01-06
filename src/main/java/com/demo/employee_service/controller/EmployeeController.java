package com.demo.employee_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.demo.employee_service.dao.EmployeeRepository;
import com.demo.employee_service.dao.entity.Employee;
import com.demo.employee_service.pojo.DepartmentPojo;
import com.demo.employee_service.pojo.EmployeePojo;
import com.demo.employee_service.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    @GetMapping("/employees/dept/{did}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable("did") int deptId)
    {
        return new ResponseEntity<List<Employee>>(empService.getEmployeesByDepartment(deptId), HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return new ResponseEntity(empService.getAllEmployee(), HttpStatus.OK);
    }

    @GetMapping("/employees/{eid}")
    public ResponseEntity<EmployeePojo> getAEmployee(@PathVariable("eid") int empId)
    {   
        Optional<Employee> emp = empService.getAEmployee(empId);
        if(emp.isPresent())
        {
            EmployeePojo employeePojo = new EmployeePojo();
            RestClient restClient = RestClient.create();
            DepartmentPojo departmentPojo = restClient
                .get()
                .uri("http://department-service:8081/api/departments/"+emp.get().getEmpDeptId())
                .retrieve()
                .body(DepartmentPojo.class);
            Optional<Employee> employee = empService.getAEmployee(empId);
            employeePojo.setEmployeeName(employee.get().getEmployeeName());
            employeePojo.setEmployeeDesignation(employee.get().getEmployeeDesignation());
            employeePojo.setEmployeeId(employee.get().getEmployeeId());
            employeePojo.setEachDept(departmentPojo);
            return new ResponseEntity<>(employeePojo, HttpStatus.OK);

        //return new ResponseEntity<EmployeePojo>(employeePojo, HttpStatus.OK);
        }
        
        return new ResponseEntity(empService.getAEmployee(empId), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee newDept)
    {
        return new ResponseEntity(empService.addEmployee(newDept), HttpStatus.OK);
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee updateDept)
    {
        return new ResponseEntity(empService.updateEmployee(updateDept), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{eid}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("eid") int deptId)
    {
        empService.deleteEmployee(deptId);
        return new ResponseEntity(HttpStatus.OK);
    } 



}
