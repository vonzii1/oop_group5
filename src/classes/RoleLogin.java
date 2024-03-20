/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import frames.frm_AdminLogin;
import frames.frm_EmployeeLogin;
import frames.frm_RoleLogin;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        String filepath = "employee-details.csv"; // Adjust this to the actual file path
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(name) && data[2].equals(password)) {
                    return data[2]; // Return the role if username and password match
                }
            }
        } catch (IOException e) {
        }
        return "unknown"; // Return "unknown" if no match found or in case of exception
    }

    public static void main(String[] args) {
        frm_RoleLogin loginFrame = new frm_RoleLogin();
        loginFrame.setVisible(true);
    }
}


//import frames.frm_AdminLogin;
//import frames.frm_EmployeeLogin;
//import frames.frm_RoleLogin;
//import java.awt.Button;
//
///**
// *
// * @author User
// */
//public class RoleLogin {
//    
//    private frm_RoleLogin loginFrame;
//    
//    private Button jButton2;
//    private Button jButton3;
//    
//    
//    public RoleLogin(String admin, String employee){
//        String role = authenticateAndGetRole(admin, employee);
//        this.loginFrame = new frm_RoleLogin();
//        
//        if ("admin".equalsIgnoreCase(role)) {
//            // User is an admin
//            frm_AdminLogin adminLogin = new frm_AdminLogin();
//            adminLogin.setVisible(true);
//        } else if ("employee".equalsIgnoreCase(role)) {
//            // User is an employee
//            frm_EmployeeLogin employeeLogin = new frm_EmployeeLogin();
//            employeeLogin.setVisible(true);
//        } else {
//            // Role is unknown or authentication failed
//            System.out.println("Authentication failed or role unknown.");
//            // Optionally, show an error message or a login failure screen
//        }
//    }
//        
//    
//
//    private String authenticateAndGetRole(String username, String password) {
//        if (null == username) {
//            return "unknown";
//        } else         // Placeholder for actual authentication and role retrieval logic
//        // This should check the user's credentials and return their role
//        // For the sake of this example, let's just return a dummy role based on the username
//        return switch (username) {
//            case "adminUser" -> "admin";
//            case "employeeUser" -> "employee";
//            default -> "unknown";
//        };
//    }
//public static void main(String[] args) {
//    frm_RoleLogin loginFrame = new frm_RoleLogin();
//    loginFrame.setVisible(true);
//}
//}
