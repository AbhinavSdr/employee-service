package com.demo.employee_service.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePojo 
{
    private int employeeId;
    private String employeeName;
    private String employeeDesignation;
    private int empDeptId;
    DepartmentPojo eachDept;
}
