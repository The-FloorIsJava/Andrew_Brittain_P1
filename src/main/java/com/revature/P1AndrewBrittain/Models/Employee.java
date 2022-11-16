package com.revature.P1AndrewBrittain.Models;

public class Employee {

        private String employeeEmail;
        private String name;
        private String password;


        // Why have the below code?
        public Employee(){}

        public Employee(String email, String name, double balance){
            this.employeeEmail = email;
            this.name = name;

        }

        // What are the below methods for? Why do we need them?
        public String getEmail(){
            return employeeEmail;
        }

        public String getName(){
            return name;
        }


    }

