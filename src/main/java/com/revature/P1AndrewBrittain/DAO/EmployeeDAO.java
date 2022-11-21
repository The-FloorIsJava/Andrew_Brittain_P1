package com.revature.P1AndrewBrittain.DAO;

import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Util.ConnectionFactory;
import com.revature.P1AndrewBrittain.Util.Exceptions.InvalidEmployeeInputException;
import com.revature.P1AndrewBrittain.Util.Interface.Crudable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements Crudable<Employee> {


    @Override
    public Employee create(Employee newEmployee) {

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

            /*String sql = "insert into employee (employee_email, employee_name, employee_password, is_manager_true) values ("
                    + "\'" + newEmployee.getEmployeeEmail() + "\'"
                    + "\'" + newEmployee.getEmployeeName() + "\'"
                    + "\'" + newEmployee.getEmployeePassword() + "\'"
                    + "\'" + newEmployee.getIsManagerTrue() + "\'";

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);*/

            String sql = "insert into employee (employee_email, employee_password, is_manager_true) values (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newEmployee.getEmployeeEmail());
            preparedStatement.setString(2, newEmployee.getEmployeePassword());
            preparedStatement.setBoolean(3, newEmployee.getIsManagerTrue());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException("Employee was not added to the database");
            }
            return newEmployee;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Employee> findAll() {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Employee> employees = new ArrayList<>();

            String sql = "select * from employee";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                employees.add(convertSqlInfoToEmployee(resultSet));
            }

            return employees;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee findByEmail(String employeeEmail) {
        return null;
    }

    @Override
    public boolean update(Employee updatedEmployee) {
        return false;
    }

    @Override
    public boolean delete(String EmployeeEmail) {
        return false;
    }

    public Employee loginCheck(String employeeEmail, String employeePassword){

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            String sql = "select * from employee where employee_email = ? and employee_password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, employeeEmail);
            preparedStatement.setString(2, employeePassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                throw new InvalidEmployeeInputException("Entered information for " + employeeEmail + " was incorrect. Please try again.");
            }
            return convertSqlInfoToEmployee(resultSet);


        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    private Employee convertSqlInfoToEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setEmployeeEmail(resultSet.getString("employee_email"));
        employee.setEmployeePassword(resultSet.getString("employee_password"));
        employee.setEmployeeIsManagerTrue(resultSet.getBoolean("is_manager_true"));

        return employee;
    }
}
