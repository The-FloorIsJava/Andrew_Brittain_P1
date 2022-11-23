package com.revature.P1AndrewBrittain.Controller;

import com.revature.P1AndrewBrittain.DAO.TicketDAO;
import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Models.Ticket;
import com.revature.P1AndrewBrittain.Service.EmployeeService;
import com.revature.P1AndrewBrittain.Service.TicketService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class TicketController {
    
    TicketService ticketService;

    private final EmployeeService employeeService;


    public TicketController(EmployeeService employeeService, TicketService ticketService) {
        this.employeeService = employeeService;
        this.ticketService = ticketService;
    }

    public void ticketEndpoint(Javalin app){
        app.post("employeetix", this::ticketSubmitHandler);
        app.get("employeetix", this::viewTicketHandler);
        app.post("process", this::processTicketHandler);
        app.get("process", this::getAllTicketHandler);
        app.get("tixhello", this::tixHelloHandler);
    }

    private void tixHelloHandler(Context context) {
        context.result("Welcome to SkyNet, John Connor");
    }

    private void ticketSubmitHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket = mapper.readValue(context.body(), Ticket.class);
        ticket = ticketService.addTicket(ticket);
        context.json(ticket);
    }

    private void viewTicketHandler(Context context) {
        Employee employee = this.employeeService.getSessionEmployee();
        if (employee == null) {
            context.json("You are not logged in");
            return;
        }
        List<Ticket> tickets = this.ticketService.getAllThisEmployeeTickets(employee);

        if (tickets == null){
            context.json("No pending tickets.");
            return;
        }
        context.json(tickets);

    }

    private void processTicketHandler(Context context) {
    }

    private void getAllTicketHandler(Context context) {
    }
}
