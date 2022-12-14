package com.revature.P1AndrewBrittain.Service;

import com.revature.P1AndrewBrittain.DAO.EmployeeDAO;
import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Util.Tokens.JWTUtility;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    public boolean isEmployeeValid(Employee newEmployee){
        if(newEmployee == null) return false;
        if(newEmployee.getEmployeeEmail() == null || newEmployee.getEmployeeEmail().trim().equals("")) return false;
        return true;
    }

    public boolean isEmailAvailable(String email){
            if(email == null) return false;
            return true;
    }





    private Employee sessionEmployee = null;

    private final EmployeeDAO employeeDAO;

    private JWTUtility jwtUtility;

    public EmployeeService(EmployeeDAO employeeDAO, JWTUtility jwtUtility){
        this.employeeDAO = employeeDAO;
        this.jwtUtility = jwtUtility;
    }
    public Employee addEmployee(Employee employee){
        List<Employee> employees = employeeDAO.findAll();
        List<String> emails = new ArrayList<>();
        boolean isUnique;

        for (int i = 0; i<employees.size(); i++){
            emails.add(employees.get(i).getEmployeeEmail());
        }
        if (emails.contains(employee.getEmployeeEmail())){
            isUnique = false;
        } else {
            isUnique = true;
        }

        if (isUnique){
            return employeeDAO.create(employee);
        } else {
            return null;
        }
    }

    public Employee getEmployee(Employee employee){
     return null;
    }

    public void removeEmployee( String employeeEmail){

    }


public Employee login(String employeeEmail, String employeePassword){
        //implement with dao
    return employeeDAO.loginCheck(employeeEmail, employeePassword);
}

public void logout(){
       this.sessionEmployee = null;
} // this could be used to log logout times

public Employee getSessionEmployee(String token){
        return this.jwtUtility.extractTokenDetails(token);
}
}
