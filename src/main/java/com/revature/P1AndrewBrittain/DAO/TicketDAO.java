package com.revature.P1AndrewBrittain.DAO;

import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Models.Ticket;
import com.revature.P1AndrewBrittain.Util.ConnectionFactory;
import com.revature.P1AndrewBrittain.Util.Exceptions.InvalidEmployeeInputException;
import com.revature.P1AndrewBrittain.Util.Interface.Crudable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements Crudable<Employee> {


    @Override
    public Ticket create(Ticket newTicket) {

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {



            String sql = "insert into ticket (amount, ticket_type, requester, ticket_approved) values (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, newTicket.getAmount());
            preparedStatement.setString(2, newTicket.getRequestType());
            preparedStatement.setString(3, newTicket.getRequester());
            preparedStatement.setBoolean(4,newTicket.isTicketApproved());

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


    @Override
    public List<Ticket> findAll() {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Ticket> tickets = new ArrayList<>();

            String sql = "select * from ticket";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                tickets.add(convertSqlInfoToTicket(resultSet));
            }

            return tickets;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }




    private Employee convertSqlInfoToTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();

        ticket.setTicketId(resultSet.getInt("ticket_id"));
        ticket.setAmount(resultSet.getDouble("amount"));
        ticket.se(resultSet.getBoolean("is_manager_true"));

        return employee;
    }
}
