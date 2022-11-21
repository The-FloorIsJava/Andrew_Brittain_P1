package com.revature.P1AndrewBrittain.Service;

import com.revature.P1AndrewBrittain.DAO.EmployeeDAO;
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

    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    public Employee addEmployee(Employee employee){
        return employeeDAO.create(employee);
    }

    public Employee getEmployee(String employeeId){
     return null;
    }

    public void removeEmployee( String employeeEmail){

    }

    public List<Employee> getAllEmployees(){
    return employeeDAO.findAll();
    }

public void login(String employeeEmail, String employeePassword){
        //implement with dao
        sessionEmployee = employeeDAO.loginCheck(employeeEmail, employeePassword);
}



public void logout(){
       this.sessionEmployee = null;
}

public Employee getSessionEmployee(){
        return this.sessionEmployee;
}
}
