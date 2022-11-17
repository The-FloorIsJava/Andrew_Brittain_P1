package com.revature.P1AndrewBrittain.Models;

public class Employee {
    private String employeeName;
    private String employeeEmail;
    private String employeePassword;
    private boolean isManagerTrue;


    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }


        public Employee(){}

        public Employee(String employeeEmail, String employeeName, String employeePassword, boolean isManagerTrue){
            this.employeeEmail = employeeEmail;
            this.employeeName = employeeName;
        }

    @Override
    public String toString() {
        return "Employee{" +
                "Employee Email: " + employeeEmail +
                ", Employee Name: " + employeeName + " , Is a Manager?: " +
                isManagerTrue +
                "}";
    }
    }

