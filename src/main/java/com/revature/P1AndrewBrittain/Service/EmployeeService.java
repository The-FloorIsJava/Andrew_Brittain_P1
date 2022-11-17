package com.revature.P1AndrewBrittain.Service;

import com.revature.P1AndrewBrittain.Models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

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

    List<Employee> employeeList;

    public EmployeeService(){
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public Employee getEmployee(String employeeName){
        for (int i = 0; i < employeeList.size(); i++){
            Employee e = employeeList.get(i);
            if (e.getEmployeeName().equals(employeeName)){
                return employeeList.get(i);
            }
        }
        return null;
    }

    public List<Employee> getAllEmployees(){
        return employeeList;
    }



}
