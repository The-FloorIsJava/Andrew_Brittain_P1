package com.revature.P1AndrewBrittain.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.revature.P1AndrewBrittain.DAO.TicketDAO;
import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Models.Ticket;
import com.revature.P1AndrewBrittain.Service.EmployeeService;
import com.revature.P1AndrewBrittain.Service.TicketService;
import com.revature.P1AndrewBrittain.Util.DTO.ProcessTicketDTO;
import com.revature.P1AndrewBrittain.Util.Tokens.JWTUtility;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class TicketController {
    
    TicketService ticketService;

    Javalin app;

    JWTUtility jwtUtility;

    private final EmployeeService employeeService;


    public TicketController(EmployeeService employeeService, TicketService ticketService, Javalin app, JWTUtility jwtUtility) {
        this.employeeService = employeeService;
        this.ticketService = ticketService;
        this.app = app;
        this.jwtUtility = jwtUtility;
    }

    public void ticketEndpoint(Javalin app){
        app.post("employeetix", this::ticketSubmitHandler);
        app.get("employeetix", this::viewTicketHandler);
        app.get("employeetix/pending", this::viewTicketPendingHandler);
        app.get("employeetix/denied", this::viewTicketDeniedHandler);
        app.get("employeetix/approved", this::viewTicketApprovedHandler);
        app.post("process", this::processTicketHandler);
        app.get("process", this::getAllTicketHandler);
    }



    private void ticketSubmitHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket = mapper.readValue(context.body(), Ticket.class);
        String token = context.header("authorization");
        ticket.setRequester(jwtUtility.extractTokenDetails(token).getEmployeeEmail());
        ticket = ticketService.addTicket(ticket);
        if (ticket != null) {
            context.status(200);
            context.json(ticket);
        } else {
            context.status(400);
        }
    }

    private void viewTicketHandler(Context context) {
        String token = context.header("authorization");
        Employee employee = this.employeeService.getSessionEmployee(token);
        if (employee == null) {
//            context.json("You are not logged in");
            context.status(403);
            return;
        }
        List<Ticket> tickets = this.ticketService.getAllThisEmployeeTickets(employee);

        if (tickets == null){
//            context.json("No current tickets.");
            context.status(404);
            return;
        }
        context.status(200);
        context.json(tickets);

    }

    private void viewTicketPendingHandler(Context context){
        String token = context.header("authorization");
        Employee employee = this.employeeService.getSessionEmployee(token);
        if (employee == null) {
            context.json("You are not logged in");
            return;
        }
        List<Ticket> pendingTickets = this.ticketService.getAllThisEmployeePendingTickets(employee);

        if (pendingTickets == null){
            context.json("No pending tickets.");
            return;
        }
        context.json(pendingTickets);
    }

    private void viewTicketApprovedHandler(Context context){
        String token = context.header("authorization");
        Employee employee = this.employeeService.getSessionEmployee(token);
        if (employee == null) {
            context.json("You are not logged in");
            return;
        }
        List<Ticket> approvedTickets = this.ticketService.getAllThisEmployeeApprovedTickets(employee);

        if (approvedTickets == null){
            context.json("No pending tickets.");
            return;
        }
        context.json(approvedTickets);
    }
    private void viewTicketDeniedHandler(Context context){
        String token = context.header("authorization");
        Employee employee = this.employeeService.getSessionEmployee(token);
        if (employee == null) {
            context.json("You are not logged in");
            return;
        }
        List<Ticket> deniedTickets = this.ticketService.getAllThisEmployeeDeniedTickets(employee);

        if (deniedTickets == null){
            context.json("No pending tickets.");
            return;
        }
        context.json(deniedTickets);
    }
    private void processTicketHandler(Context context) throws JsonProcessingException {
        String token = context.header("authorization");
        if (!managerAccess(token)){
            context.json("This requires Manager credentials.");
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            ProcessTicketDTO processTicketDTO = objectMapper.readValue(context.body(), ProcessTicketDTO.class);
            context.json(processTicketDTO);
            ticketService.updateTicket(processTicketDTO.getTicketId(), processTicketDTO.getIsTicketApproved());
        }
    }

    private void getAllTicketHandler(Context context) {
        String token = context.header("authorization");
        if (managerAccess(token)){
            List<Ticket> managerPendingTickets = this.ticketService.getManagerPendingTickets();
            if (managerPendingTickets == null){
                context.status(404);
            } else{
                context.json(managerPendingTickets);
                context.status(200);
            }
        }else{
            context.status(403);
        }
      }

    private boolean managerAccess (String token){
        Employee employee = this.employeeService.getSessionEmployee(token);
        if (employee.getIsManagerTrue()){
            return true;
        } else {
            return false;
        }
    }
}
