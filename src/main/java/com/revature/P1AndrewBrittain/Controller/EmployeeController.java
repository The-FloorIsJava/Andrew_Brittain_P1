package com.revature.P1AndrewBrittain.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Service.EmployeeService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class EmployeeController {

    EmployeeService employeeService;

    Javalin app;

    public EmployeeController(Javalin app){
        employeeService = new EmployeeService();
        this.app=app;
    }

    public void employeeEndpoint(){

        app.get("hello", this::helloHandler);
        app.post("employee", this::postEmployeeHandler);
        app.get("employee", this::getAllEmployeeHandler);

    }

    private void getAllEmployeeHandler(Context context) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        context.json(allEmployees);
    }

    private void postEmployeeHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
    Employee employee = mapper.readValue(context.body(), Employee.class);
    employeeService.addEmployee(employee);
    context.json(employee);
    }


    private void helloHandler(Context context) {
        context.result("Welcome to SkyNet");
    }


}
