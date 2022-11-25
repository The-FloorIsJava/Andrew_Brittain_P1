package com.revature.P1AndrewBrittain.Service;

import com.revature.P1AndrewBrittain.DAO.EmployeeDAO;
import com.revature.P1AndrewBrittain.DAO.TicketDAO;
import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Models.Ticket;

import java.util.ArrayList;
import java.util.List;


public class TicketService {
    private final TicketDAO ticketDAO;
    public TicketService(TicketDAO ticketDAO){

        this.ticketDAO = ticketDAO;
    }

    public List<Ticket> getAllThisEmployeeTickets(Employee employee){

        return ticketDAO.getAllThisEmployeeTickets(employee);
    }

    public Ticket addTicket(Ticket ticket){

        return ticketDAO.create(ticket);
    }


    public List<Ticket> getAllThisEmployeePendingTickets(Employee employee) {
        return ticketDAO.getAllThisEmployeePendingTickets(employee);
    }

    public List<Ticket> getAllThisEmployeeApprovedTickets(Employee employee) {
        return ticketDAO.getAllThisEmployeeApprovedTickets(employee);
    }

    public List<Ticket> getAllThisEmployeeDeniedTickets(Employee employee) {
        return ticketDAO.getAllThisEmployeeDeniedTickets(employee);
    }
}
