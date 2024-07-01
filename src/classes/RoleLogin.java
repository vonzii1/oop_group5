/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import frames.frm_AdminLogin;
import frames.frm_EmployeeLogin;
import frames.frm_RoleLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleLogin {

    public RoleLogin() {
        // Constructor might be empty or set up as needed without immediate authentication
    }

    public void authenticateAndNavigate(String name, String password) {
        String role = authenticateAndGetRole(name, password);

        if ("admin".equalsIgnoreCase(role)) {
            frm_AdminLogin adminLogin = new frm_AdminLogin();
            adminLogin.setVisible(true);
        } else if ("employee".equalsIgnoreCase(role)) {
            frm_EmployeeLogin employeeLogin = new frm_EmployeeLogin();
            employeeLogin.setVisible(true);
        } else {
            System.out.println("Authentication failed or role unknown.");
        }
    }

    private String authenticateAndGetRole(String name, String password) {
        String role = "unknown";
        String url = "jdbc:mysql://localhost:3306/payrollsystem_db"; // Modify this with your actual database URL
        String user = "root"; // Modify this with your actual database username
        String pass = "root"; // Modify this with your actual database password
        String query = "SELECT role FROM users WHERE username = ? AND password = ?";
        

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                role = rs.getString("role");
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return role;
    }

    public static void main(String[] args) {
        frm_RoleLogin loginFrame = new frm_RoleLogin();
        loginFrame.setVisible(true);
    }
}
