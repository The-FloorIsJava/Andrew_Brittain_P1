package com.revature.P1AndrewBrittain.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1AndrewBrittain.DAO.EmployeeDAO;
import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Service.EmployeeService;
import com.revature.P1AndrewBrittain.Util.DTO.LoginCreds;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class EmployeeController {
    EmployeeService employeeService;
    Javalin app;
    public EmployeeController(Javalin app){
        employeeService = new EmployeeService(new EmployeeDAO());
        this.app=app;
    }
    public void employeeEndpoint(){
        app.get("hello", this::helloHandler);
        app.post("register", this::postEmployeeHandler);
        app.post("login", this::loginHandler);
        app.delete("logout", this::logoutHandler);
        app.get("allemployees", this::getAllEmployeeHandler);
        app.get("employee/{employeeName}", this::getSpecificEmployeeHandler);
    }
    private void helloHandler(Context context) {

        context.result("Welcome to SkyNet");
    }
    private void postEmployeeHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper.readValue(context.body(), Employee.class);
        employeeService.addEmployee(employee);
        context.json(employee);
    }
    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginCreds loginCreds = mapper.readValue(context.body(), LoginCreds.class);
        employeeService.login(loginCreds.getEmployeeName(), loginCreds.getEmployeePassword());
        context.json("Successfully logged in!");
    }
    private void logoutHandler(Context context) {
        String employeeName = employeeService.getSessionEmployee().getEmployeeEmail();
        employeeService.logout();
        context.json(employeeName + "is now logged out");
    }
    private void getAllEmployeeHandler(Context context) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        context.json(allEmployees);
    }
    private void getSpecificEmployeeHandler(Context context){
        String employeeEmail = context.pathParam("employeeEmail");
        Employee employee = employeeService.getEmployee(employeeEmail);
        context.json(employee);
    }






}
