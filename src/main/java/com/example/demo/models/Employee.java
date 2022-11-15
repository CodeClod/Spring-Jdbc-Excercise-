package com.example.demo.models;

public class Employee {
    int EmployeeId;
    String name;

    public Employee(int employeeId, String name) {
        EmployeeId = employeeId;
        this.name = name;
    }
    public Employee(String name) {
        setName(name);
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
