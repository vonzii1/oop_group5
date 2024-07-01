/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author User
 */

import frames.frm_EmployeeRecords;
import java.sql.*;
import javax.swing.*;

public class HRManagement {
    private frm_EmployeeRecords employeeRecordsFrame;
   

    public HRManagement() {
        employeeRecordsFrame = new frm_EmployeeRecords();
        setUpActionListeners();
        employeeRecordsFrame.setVisible(true);
       
    }

    void setUpActionListeners() {
        employeeRecordsFrame.getAddButton().addActionListener(e -> handleAddEmployee());
        employeeRecordsFrame.getDeleteButton().addActionListener(e -> handleDeleteEmployee());
        employeeRecordsFrame.getUpdateButton().addActionListener(e -> handleModifyEmployee());
        employeeRecordsFrame.getExitButton().addActionListener(e -> System.exit(0));
    }

    void handleAddEmployee() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Employee (...) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(employeeRecordsFrame, "Employee added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void handleDeleteEmployee() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Employee WHERE ...";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(employeeRecordsFrame, "Employee deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void handleModifyEmployee() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE Employee SET ... WHERE ...";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(employeeRecordsFrame, "Employee updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Ensuring the application starts safely within the Swing event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    HRManagement hrManagement = new HRManagement();
                    System.out.println("HR Management system is up and running!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}