package com.revature.P1AndrewBrittain;

import com.revature.P1AndrewBrittain.Controller.EmployeeController;
import com.revature.P1AndrewBrittain.Controller.TicketController;
import com.revature.P1AndrewBrittain.DAO.EmployeeDAO;
import com.revature.P1AndrewBrittain.DAO.TicketDAO;
import com.revature.P1AndrewBrittain.Service.EmployeeService;
import com.revature.P1AndrewBrittain.Service.TicketService;
import com.revature.P1AndrewBrittain.Util.Tokens.JWTUtility;
import io.javalin.Javalin;

public class JavalinApp {


    public static void main(String[] args) {

        Javalin app = Javalin.create(
                config -> {
                    config.plugins.enableCors(cors ->{
                        cors.add(it ->{
                            it.anyHost();
                            it.exposeHeader("Authorization");
                        });
                    });
                }
        ).start(8080);

        JWTUtility jwtUtility = new JWTUtility();

        EmployeeDAO employeeDAO = new EmployeeDAO();
        TicketDAO ticketDAO = new TicketDAO();

        EmployeeService employeeService = new EmployeeService(employeeDAO, jwtUtility);
        TicketService ticketService = new TicketService(ticketDAO);

        EmployeeController employeeController = new EmployeeController(app, employeeService, jwtUtility);
        TicketController ticketController = new TicketController(employeeService, ticketService, app, jwtUtility);

        employeeController.employeeEndpoint(app);
        ticketController.ticketEndpoint(app);
    }
}
