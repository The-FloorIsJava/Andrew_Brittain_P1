package com.revature.P1AndrewBrittain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1AndrewBrittain.Controller.EmployeeController;
import com.revature.P1AndrewBrittain.Controller.TicketController;
import com.revature.P1AndrewBrittain.DAO.EmployeeDAO;
import com.revature.P1AndrewBrittain.DAO.TicketDAO;
import com.revature.P1AndrewBrittain.Service.EmployeeService;
import com.revature.P1AndrewBrittain.Service.TicketService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class JavalinApp {
    public static void main(String[] args) {

      /*  EmployeeDAO employeeDAO = new EmployeeDAO();
        TicketDAO ticketDAO = new TicketDAO();
        EmployeeService employeeService = new EmployeeService(employeeDAO);
        TicketService ticketService = new TicketService(ticketDAO);

        EmployeeController employeeController = new EmployeeController(employeeService);
        TicketController ticketController = new TicketController(employeeService, ticketService);



       */


        Javalin app = Javalin.create()
                .start(8080);

        new EmployeeController(app).employeeEndpoint();
        new TicketController(app).ticketEndpoint();


    }
}
