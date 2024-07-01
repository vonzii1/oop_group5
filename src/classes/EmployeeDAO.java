/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author User
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public void addEmployee(String name, String address, String contact, String position, String dob, String status, double salary, int gsm_rate, double hourly_rate, String sssId, String philhealthId, String birNumber, String pagibigId) {
        String sql = "INSERT INTO Employee (employeeName, employeeAddress, contactDetails, position, employeeDOB, status, employeeSalary, GSM_rate, hourly_rate, employeeSSSID, employeePhilhealthID, employeeBIRNumber, employeePagibigID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, contact);
            statement.setString(4, position);
            statement.setString(5, dob);
            statement.setString(6, status);
            statement.setDouble(7, salary);
            statement.setInt(8, gsm_rate);
            statement.setDouble(9, hourly_rate);
            statement.setString(10, sssId);
            statement.setString(11, philhealthId);
            statement.setString(12, birNumber);
            statement.setString(13, pagibigId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // This will print the stack trace of the exception
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT employeeId, employeeName FROM Employee";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employeeId");
                String employeeName = resultSet.getString("employeeName");
                employees.add(new Employee(employeeId, employeeName));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // This will print the stack trace of the exception
        }
        return employees;
    }

    public boolean login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void modifyEmployee(int employeeId, String jane_Smith, String _Elm_St, String string, String senior_Developer, String string0, double d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void deleteEmployee(int employeeIdToDelete) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}