/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import frames.frm_AdminDashboard;
import frames.frm_EmployeeDashboard;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;

public class LoginController {

    private static final String USER_DATA_FILE = "employee-details.csv";

    public void loginUser(String username, String password) {
        String role = authenticateUser(username, password);

        if (null == role) {
            System.out.println("Authentication failed. Please check your name and password.");
        } else switch (role) {
            case "admin" -> openAdminDashboard();
            case "employee" -> openEmployeeDashboard();
            default -> System.out.println("Authentication failed. Please check your name and password.");
        }
    }

    private String authenticateUser(String name, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(name) && values[1].equals(password)) {
                    return values[2]; // Return the role if a matching name and password are found
                }
            }
        } catch (IOException e) {
        }
        return ""; // Return an empty string if authentication fails
    }

    private void openEmployeeDashboard() {
        System.out.println("Opening Employee Dashboard...");
        frm_EmployeeDashboard employeeDashboard = new frm_EmployeeDashboard();
        employeeDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeDashboard.setVisible(true);
    }

    private void openAdminDashboard() {
        System.out.println("Opening Admin Dashboard...");
        frm_AdminDashboard adminDashboard = new frm_AdminDashboard();
        adminDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminDashboard.setVisible(true);
    }
}

//import frames.frm_EmployeeLogin;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.net.URL;
//
//import javax.swing.JFrame;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//import javax.swing.SwingUtilities;
//
//public class LoginController {
//    
//    private JTextField jTextField3;
//    private JPasswordField jPasswordField1;
//    
//    JFrame frm_EmployeeLogin;
//    
//    frm_EmployeeLogin employeeLoginFrame;
//    static String emp_number = "";
//     static String pass = "";
//     static String name = "";
//     static String bday = "";
//     static String month = "";
//     static double basic_salary = 0;
//     static double rice_subsidy = 0;
//     static double phone_allowance = 0;
//     static double clothing_allowance = 0;
//     static double hourly_rate = 0d;
//     static double gross = 0;
//     static double sss = 0;
//     static double pagibig = 0;
//     static double phealth = 0;
//     static double withholding = 0;
//     static double perks = 0;
//     static double deductions = 0;
//     static double net = 0;
//     static double totalh = 0;
//     static double leave = 0;
//    private String ss;
//    private String wh;
//    private String pag;
//    private String ph;
//    private String td;
//    private String th;
//    private String tp;
//    private String ni;
//    private String gros;
//    private String lea;
//    private String rice;
//    private String phone;
//    private String clothing;
//    private String hourly;
//   
//    
//    public void userLogin(java.awt.event.ActionEvent event) throws IOException {
//        checkLogin();
//    }
//    
//    private void checkLogin()throws IOException{
//        
//        String name = jTextField3.getText();
//        String pass = jPasswordField1.getText();
//        String employee = "employee";
//        
//        try {
//            // create br variable to hold csv file data
//            // open our csv file with FileReader giving our path with csv_file variable
//            BufferedReader br = new BufferedReader(new FileReader("employee-details.csv"));
//            String line = "";
//
//            // create a while loop and assign our csv file to line variable
//            // until the end of the file {null}
//            while((line = br.readLine()) != null) {
//                // create an array of string variable that will hold each line
//                // each array element is separated by comma
//                // this will result to: employee = {"10001","Juan","Dela Cruz","etc"}
//                // to access each element we have to declare: employee[0] for 10001, employee[1] for Juan and etc
//                String[] details = line.split(",");
//                
//                if(details[0].equals(name) && details[1].equals(pass)) {
//                    //wrongLogIn.setText("Success!");
//                    // put all details in the variables
//                    if (details[0].equals(employee)){
//                       
//                        Oop_ms2.setRoot("employee");
//                    }else {
//                    emp_number = details[0];
//                    pass = details[1];
//                    name = details[2];
//                    bday = details[3];
//                    basic_salary = Double.parseDouble(details[4]);
//                    rice_subsidy = Double.parseDouble(details[5]);
//                    phone_allowance = Double.parseDouble(details[6]);
//                    clothing_allowance = Double.parseDouble(details[7]);
//                    month = details[8];
//                    hourly_rate = Double.parseDouble(details[9]);
//                    leave = Double.parseDouble(details[10]);
//
//                    
//
//                    print_details();
//                    
//                    String ss = String.valueOf(Math.round(sss));                    
//                    String gros = Double.toString(Math.round(gross));
//                    String pag = String.valueOf(Math.round(pagibig));
//                    String ph = String.valueOf(Math.round(phealth));
//                    String wh = String.valueOf(Math.round(withholding));
//                    String td = String.valueOf(Math.round(deductions));
//                    String th = String.valueOf(Math.round(totalh));
//                    String tp = String.valueOf(Math.round(perks));
//                    String ni = String.valueOf(Math.round(net));
//                    String lea = String.valueOf(Math.round(leave));
//                    String basic = String.valueOf(Math.round(basic_salary));
//                    String rice = String.valueOf(Math.round(rice_subsidy));
//                    String phone = String.valueOf(Math.round(phone_allowance));
//                    String clothing = String.valueOf(Math.round(clothing_allowance));
//                    String hourly = String.valueOf(Math.round(hourly_rate));
//
//                    
//                    JFrameLoader loader = new JFrameLoader(getClass().getResource("employeepayslip.jframe"));
//                    loader.load();
//                    PayrollFormController finance = loader.getController();
//                    String[] empData = {emp_number,bday,name,ss,pag,ph,wh,td,th,tp,ni,gros,lea,pass,basic,rice,phone,clothing,month,hourly};
//                    PayrollFormController.displayDetails(empData);
//                    
//                        Oop_ms2.setRoot("finance");
//                    }
//                    }else if (name.isEmpty() && pass.isEmpty()) {
//                    //wrongLogIn.setText("Please enter you data.");
//                }
//                else {
//                    //wrongLogIn.setText("Wrong username or password!!");
//                }
//            }
//
//            br.close();
//        } catch (Exception e) {
//            // TODO: handle exception
//            //wrongLogIn.setText("Error Encountered!");
//        }       
//        
//    }
//
//    private void print_details() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    private static class JFrameLoader {
//    private final PayrollFormController payrollFrame;
//
//    public JFrameLoader(URL resource) {
//        // The URL resource is not used here because Swing doesn't load JFrames from URLs.
//        // Instead, we'll directly create an instance of the Finance JFrame.
//        payrollFrame = new PayrollFormController();
//    }
//
//    public PayrollFormController getController() {
//        // Return the 'controller', which is the JFrame itself in this context.
//        return payrollFrame;
//    }
//
//    public void load() {
//        // Here you can 'load' or simply make the JFrame visible.
//        // This could also be a place to do additional setup if needed.
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                payrollFrame.setVisible(true);
//            }
//        });
//    }
//}
//    
//}
//    
//
//    