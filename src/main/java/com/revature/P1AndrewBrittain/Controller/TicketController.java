package com.revature.P1AndrewBrittain.Controller;

import com.revature.P1AndrewBrittain.Service.EmployeeService;
import com.revature.P1AndrewBrittain.Service.TicketService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TicketController {
    
    TicketService ticketService;
    Javalin app;
    
    public TicketController(Javalin app){
        ticketService = new TicketService();
        this.app=app;
    }
    public void ticketEndpoint(){
        app.post("employeetix", this::ticketSubmitHandler);
        app.get("employeetix", this::viewTicketHandler);
        app.post("process", this::processTicketHandler);
        app.get("process", this::getAllTicketHandler);
        app.get("tixhello", this::tixHelloHandler);

        
    }

    private void tixHelloHandler(Context context) {
        context.result("Welcome to SkyNet, John Connor");
    }

    private void ticketSubmitHandler(Context context) {
    }

    private void viewTicketHandler(Context context) {
    }

    private void processTicketHandler(Context context) {
    }

    private void getAllTicketHandler(Context context) {
    }
}
