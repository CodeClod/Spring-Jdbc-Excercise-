package com.example.demo.repositories;

import com.example.demo.models.Employee;
import com.example.demo.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IRepository<Employee> {


    Connection conn = DatabaseConnectionManager.getConnection();
    @Override
    public List getAllEntities() {
        List<Employee> allEmployees = new ArrayList<Employee>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employees");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Employee temp = new Employee(
                        rs.getInt(1),
                        rs.getString(2)
                );
                allEmployees.add(temp);
            }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allEmployees;
    }


    @Override
    public Employee getSingleById(int id) {
        Employee employee = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("Select * FROM employees WHERE EmployeeID = ?");
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                employee = new Employee(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error in Employee Repo");
        }
        return employee;
    }

    @Override
    public boolean create(Employee entity) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO employees (Name) VALUES (?)");
            pstmt.setString(1, entity.getName());
            return true;

        } catch (SQLException e) {
            System.out.println("Employee Repo /create() error");
            return false;
        }
    }
}
