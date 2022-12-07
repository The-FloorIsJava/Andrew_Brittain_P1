package com.revature.P1AndrewBrittain.Models;

public class Employee {
    private String employeeEmail;
    private String employeePassword;
    private boolean isManagerTrue;

    public Employee(){}

    public Employee(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Employee(String employeeEmail, String employeePassword) {
        this.employeeEmail = employeeEmail;
        this.employeePassword = employeePassword;
        this.isManagerTrue = false;
    }
    public Employee(String employeeEmail, String employeePassword, boolean isManagerTrue){
        this.employeeEmail = employeeEmail;
        this.employeePassword = employeePassword;
        this.isManagerTrue = isManagerTrue;
    }
    public String getEmployeeEmail() {
        return employeeEmail;
    }
    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
    public String getEmployeePassword() {
        return employeePassword;
    }
    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }
    public boolean getIsManagerTrue() {
        return isManagerTrue;
    }
    public void setEmployeeIsManagerTrue(boolean isManagerTrue) {
        this.isManagerTrue = isManagerTrue;
    }

    public boolean isValidEmployee(){
        return (this.employeeEmail != null) &(this.employeePassword != null);
    }
    public boolean isValidPassword(String password){
        if (password == null) return false;
        return this.employeePassword != null && this.employeePassword.equals(password);
    }
    private enum Tier{
        EMPLOYEE,
        MANAGER
    }
}

