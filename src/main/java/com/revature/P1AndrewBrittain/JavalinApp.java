package com.revature.P1AndrewBrittain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1AndrewBrittain.Controller.EmployeeController;
import com.revature.P1AndrewBrittain.Controller.TicketController;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class JavalinApp {
    public static void main(String[] args) {

        Javalin app = Javalin.create()
                .start(8080);

        new EmployeeController(app).employeeEndpoint();
        new TicketController(app).ticketEndpoint();


    }
}
