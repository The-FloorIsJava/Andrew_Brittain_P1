package com.revature.P1AndrewBrittain.Models;

public class Employee {

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    private String employeeEmail;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    private String employeeName;

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    private String employeePassword;



        public Employee(){}

        public Employee(String employeeEmail, String employeeName, double balance){
            this.employeeEmail = employeeEmail;
            this.employeeName = employeeName;

        }


    }

