package com.revature.P1AndrewBrittain.Service;

import com.revature.P1AndrewBrittain.Models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
/*
    public boolean isEmployeeValid(Employee newEmployee){
        if(newEmployee == null) return false;
        if(newEmployee.getEmployeeEmail() == null || newEmployee.getEmployeeEmail().trim().equals("")) return false;
        if(newEmployee.getEmployeeName() == null || newEmployee.getEmployeeName().trim().equals("")) return false;
        return true;
    }

    public boolean isEmailAvailable(String email){
        for(Employee employee: employeeList){
            if(employee == null) break;
            if(employee.getEmployeeEmail().equals(email)){
                return false;
            }
        }
        return true;
    }
    */

    private Employee sessionEmployee = null;


    public EmployeeService(){
    }

    public void addEmployee(Employee employee){
    }

    public Employee getEmployee(String employeeName){
     return null;
    }

    public void removeEmployee( String employeeEmail){

    }

    public List<Employee> getAllEmployees(){
    return null;
    }

public void login(){
        //implement with dao
        sessionEmployee = null;
}



public void logout(){
       sessionEmployee = null;
}

public Employee getSessionEmployee(){
        return sessionEmployee;
}
}
