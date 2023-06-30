package com.example.case_study_module3.dao;

import com.example.case_study_module3.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
    Connect connect = new Connect();
    private final String SELECT_EMPLOYEE =
            "select * from Employee" +
                    " join Contract on Employee.Id=Contract.IdEmployee" +
                    " where Employee.Id=? and Employee.Password=? and Contract.status=true";
    private  final String SELECT_ID=" select *from Employee join Contract on Employee.Id=Contract.IdEmployee where Employee.Id=?  and Contract.status=true;";

    public Employee getEmployee(int id, String password) {
        Employee employee=null;
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE)) {
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idPosition = resultSet.getInt("IdPosition");
                String name = resultSet.getString("Name");
                employee= new Employee(id, password, idPosition, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return employee;
    }

    public boolean getId(int id) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
               return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }



}
