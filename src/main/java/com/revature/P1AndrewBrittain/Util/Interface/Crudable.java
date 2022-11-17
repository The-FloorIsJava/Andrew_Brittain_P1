package com.revature.P1AndrewBrittain.Util.Interface;

import com.revature.P1AndrewBrittain.Models.Employee;

import java.util.List;

public interface Crudable<T> {

    //create
    T create (T newObject);

    //read
List<T> findAll();
T findByEmail(String employeeEmail);

    //update
boolean update(T updatedObject);

    //delete
    boolean delete(String EmployeeEmail);

}
