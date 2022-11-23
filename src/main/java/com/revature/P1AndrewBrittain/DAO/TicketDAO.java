package com.revature.P1AndrewBrittain.DAO;

import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Models.Ticket;
import com.revature.P1AndrewBrittain.Util.ConnectionFactory;
import com.revature.P1AndrewBrittain.Util.Interface.Crudable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public Ticket create(Ticket newTicket) {

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {



            String sql = "insert into ticket (amount, ticket_type, requester) values (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, newTicket.getAmount());
            preparedStatement.setString(2, newTicket.getRequestType());
            preparedStatement.setString(3, newTicket.getRequester());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException("Employee was not added to the database");
            }
            return newTicket;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Ticket> getAllThisEmployeeTickets(Employee employee) {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Ticket> tickets = new ArrayList<>();

            String sql = "select * from ticket where requester = ? order by ticket.ticket_id";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getEmployeeEmail());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                tickets.add(convertSqlInfoToTicket(resultSet));
            }

            return tickets;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    private Ticket convertSqlInfoToTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();

        ticket.setTicketId(resultSet.getInt("ticket_id"));
        ticket.setAmount(resultSet.getDouble("amount"));
        ticket.setRequester(resultSet.getString("requester"));
        ticket.setIsTicketApproved(resultSet.getString("ticket_approved"));
        ticket.setRequestType(resultSet.getString("ticket_type"));


        return ticket;
    }
}
