package com.revature.P1AndrewBrittain.DAO;

import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Util.ConnectionFactory;
import com.revature.P1AndrewBrittain.Util.Exceptions.InvalidEmployeeInputException;
import com.revature.P1AndrewBrittain.Util.Interface.Crudable;

import java.sql.*;
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

            String sql = "insert into employee (employee_email, employee_name, employee_password, is_manager_true) values (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newEmployee.getEmployeeEmail());
            preparedStatement.setString(1, newEmployee.getEmployeePassword());
            preparedStatement.setBoolean(1, newEmployee.getIsManagerTrue());

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
        return null;
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

    public Employee loginCheck(String employeeName, String employeePassword){

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            String sql = "select * from customer where employee_name = ? and employee_password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, employeeName);
            preparedStatement.setString(2, employeePassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()){
                throw new InvalidEmployeeInputException("Entered information for " + employeeName + " was incorrect. Please try again.");
            }

            Employee employee = new Employee();
            employee.setEmployeeEmail(resultSet.getString("employee_email"));
            employee.setEmployeePassword(resultSet.getString("employee_password"));
            employee.setEmployeeIsManagerTrue(resultSet.getBoolean("is_manager_true"));

            return employee;



        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
}
