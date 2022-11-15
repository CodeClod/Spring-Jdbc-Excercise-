package com.example.demo.repositories;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.utility.DatabaseConnectionManager;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DepartmentRepository implements IRepository<Department>{

    Connection conn = DatabaseConnectionManager.getConnection();
    @Override
    public List<Department> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Department> allDepartments = new ArrayList<Department>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM departments");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Department temp = new Department(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                allDepartments.add(temp);
            }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allDepartments;
    }

    @Override
    public Department getSingleById(int id) {
        Department department = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM departments WHERE departmentName = ?");
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                department = new Department(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }
        } catch (SQLException e) {
            System.out.println("Employee Repo /create() error");
        }
        return department;
    }

    @Override
    public boolean create(Department entity) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO departments (departmentNam, departmentlocation) VALUES (?,?)");
            pstmt.setString(1, entity.getDeptName());
            pstmt.setString(2, entity.getLocation());
            return true;
        } catch (SQLException e) {
            System.out.println("Employee Repo /create() error");
            return false;
        }
    }
}
