package com.revature.P1AndrewBrittain.DAO;

import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Util.ConnectionFactory;
import com.revature.P1AndrewBrittain.Util.Interface.Crudable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class EmployeeDAO implements Crudable<Employee> {


    @Override
    public Employee create(Employee newEmployee) {

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

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
            preparedStatement.setString(1, newEmployee.getEmployeeName());
            preparedStatement.setString(1, newEmployee.getEmployeePassword());
            preparedStatement.setBoolean(1, newEmployee.getIsManagerTrue());

            preparedStatement.executeUpdate();




        }catch(Exception e){

            e.printStackTrace();
            return null;
        }

        return null;
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
        return null;
    }
}
