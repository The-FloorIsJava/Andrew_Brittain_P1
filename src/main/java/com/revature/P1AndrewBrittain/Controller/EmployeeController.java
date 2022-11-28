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

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    public void employeeEndpoint(Javalin app){
        app.post("register", this::postEmployeeHandler);
        app.post("login", this::loginHandler);
        app.delete("logout", this::logoutHandler);
    }

    private void postEmployeeHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper.readValue(context.body(), Employee.class);
        if (!employee.isValidEmployee()) {
            context.json("You must include email and password to register.");
        }
        employee = employeeService.addEmployee(employee);
        if (employee == null) {
            context.json("Your email is already registered.");
        }else {
            context.json(employee);
        }
    }
    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginCreds loginCreds = mapper.readValue(context.body(), LoginCreds.class);
        employeeService.login(loginCreds.getEmployeeEmail(), loginCreds.getEmployeePassword());
        context.json("Successfully logged in!");
    }
    private void logoutHandler(Context context) {
        String employeeEmail = employeeService.getSessionEmployee().getEmployeeEmail();
        employeeService.logout();
        context.json(employeeEmail + " is now logged out");
    }

    }







