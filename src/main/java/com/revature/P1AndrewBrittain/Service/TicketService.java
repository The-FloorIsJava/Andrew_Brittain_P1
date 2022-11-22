package com.revature.P1AndrewBrittain.Service;

import com.revature.P1AndrewBrittain.DAO.EmployeeDAO;
import com.revature.P1AndrewBrittain.DAO.TicketDAO;
import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Models.Ticket;

import java.util.List;

private final TicketDAO ticketDAO;
public class TicketService {
    public TicketService(TicketDAO ticketDAO){
        this.ticketDAO = ticketDAO;
    }

    public List<Ticket> getAllThisEmployeeTickets(){
        return ticketDAO.findAll();
    }
}
