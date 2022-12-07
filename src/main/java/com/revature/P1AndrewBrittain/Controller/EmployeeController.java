package com.revature.P1AndrewBrittain.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1AndrewBrittain.DAO.EmployeeDAO;
import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Service.EmployeeService;
import com.revature.P1AndrewBrittain.Util.DTO.LoginCreds;
import com.revature.P1AndrewBrittain.Util.Exceptions.InvalidEmployeeInputException;
import com.revature.P1AndrewBrittain.Util.Tokens.JWTUtility;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.io.IOException;
import java.util.List;

public class EmployeeController {

    private final EmployeeService employeeService;

    Javalin app;

    JWTUtility jwtUtility;


    public EmployeeController(Javalin app, EmployeeService employeeService, JWTUtility jwtUtility){
        this.app = app;
        this.jwtUtility = jwtUtility;
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
            context.status(400);
//            context.json("Your email is already registered.");
        }else {
            context.status(200);
            context.json(employee);
        }
    }
    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginCreds loginCreds = mapper.readValue(context.body(), LoginCreds.class);
        try{
        Employee employee = employeeService.login(loginCreds.getEmployeeEmail(), loginCreds.getEmployeePassword());
        String token = jwtUtility.createToken(employee);

        context.header("Authorization", token);
        context.status(200);
        context.json(employee);
//        context.json("Successfully logged in!");

        } catch (InvalidEmployeeInputException e){
            context.status(404);
//            context.json(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            context.status(500);
//            context.json("The developers need to fix something, apologies for any inconvience");
        }

    }
        private void logoutHandler(Context context) {
//        String employeeEmail = employeeService.getSessionEmployee().getEmployeeEmail();
        employeeService.logout();
//        context.json(employeeEmail + " is now logged out");
            context.status(200);
    }

    }







