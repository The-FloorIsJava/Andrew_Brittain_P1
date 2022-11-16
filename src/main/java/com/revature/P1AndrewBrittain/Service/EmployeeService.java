package com.revature.P1AndrewBrittain.Service;

import com.revature.P1AndrewBrittain.Models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    List<Employee> employeeRoll;

    public EmployeeService(){
        employeeRoll = new ArrayList<>();
    }

    public boolean isEmployeeValid(Employee newEmployee){
        if(newEmployee == null) return false;
        if(newEmployee.getEmail() == null || newEmployee.getEmail().trim().equals("")) return false;
        if(newEmployee.getName() == null || newEmployee.getName().trim().equals("")) return false;
        return true;
    }

    public boolean isEmailAvailable(String email){
        for(Employee employee: employeeRoll){
            if(employee == null) break;
            if(employee.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }

}
