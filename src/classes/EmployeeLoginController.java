/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author User
 */
import frames.frm_EmployeeDashboard;
import frames.frm_EmployeeLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class EmployeeLoginController extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public EmployeeLoginController() {
         new frm_EmployeeLogin().setVisible(true); // Assume MainDashboard is your next JFrame
             this.dispose();

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin("validUsername", "validPassword");
            }
        });

        // JFrame settings
        setTitle("Employee Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    boolean handleLogin(String validUsername, String validPassword) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Employee WHERE employeeName = ? AND employeePassword = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                // Proceed to the next screen
                new frm_EmployeeDashboard().setVisible(true); // Assume MainDashboard is your next JFrame
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    
    }

    public static void main(String[] args) {
        new EmployeeLoginController().setVisible(true);
    }

    void showMessageDialog(JFrame any, String eq) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setEmployeeDashboard(frm_EmployeeDashboard employeeDashboard) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

